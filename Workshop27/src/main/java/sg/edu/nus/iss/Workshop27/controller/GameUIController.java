package sg.edu.nus.iss.Workshop27.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.iss.Workshop27.model.Comment;
import sg.edu.nus.iss.Workshop27.model.Game;
import sg.edu.nus.iss.Workshop27.model.SearchByNameResult;
import sg.edu.nus.iss.Workshop27.service.CommentService;
import sg.edu.nus.iss.Workshop27.service.GameService;

@Controller
@RequestMapping
public class GameUIController {

    @Autowired
    GameService gameService;

    @Autowired 
    CommentService commentService;

    @GetMapping("/")
    public String getHomePage(@RequestParam (defaultValue = "0") int offset,
    @RequestParam (defaultValue = "25") int limit, Model model){

        SearchByNameResult topResult = gameService.getGamesByRank(offset, limit);
        model.addAttribute("topResult", topResult);
        return "home";
    }
    
    @GetMapping("/games")
    public String getGamesByName(@RequestParam String name, @RequestParam (defaultValue = "0") int offset,
    @RequestParam (defaultValue = "25") int limit, Model model){

        SearchByNameResult result = gameService.getGamesByName(name, offset, limit);
        model.addAttribute("result", result);
        return "results";
    }

    @GetMapping("/games/{id}")
    public ModelAndView getGamesById(@PathVariable Integer id){

        Optional<Game> optGame = gameService.getGameById(id);

        ModelAndView mav = new ModelAndView();
        
        if(optGame.isEmpty()){

            mav.setViewName("not-found");
            mav.setStatus(HttpStatusCode.valueOf(404));
            return mav;
        }
        Game game = optGame.get();
        Double average = commentService.getAverage(game.getGame_id());
        game.setAverage(average);

        mav.setViewName("game-details");
        mav.addObject("game", game);
        mav.setStatus(HttpStatusCode.valueOf(200));
        return mav;
    }

    @PostMapping(path="/review")
    public String postReview(@RequestBody MultiValueMap<String, String> form){

        Comment comment = new Comment();
        comment.setUser(form.getFirst("user"));
        comment.setRating(Integer.parseInt(form.getFirst("rating")));
        comment.setComment(form.getFirst("comment"));
        comment.setGid(Integer.parseInt(form.getFirst("gid")));
        comment.setPosted(new Date());
        commentService.postComment(comment);
        
        return "posted";
    }


}
