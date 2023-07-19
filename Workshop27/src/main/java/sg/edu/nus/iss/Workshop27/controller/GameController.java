package sg.edu.nus.iss.Workshop27.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.Workshop27.model.SearchByNameResult;
import sg.edu.nus.iss.Workshop27.service.GameService;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping("/games")
    public ResponseEntity<SearchByNameResult> getGamesByName(@RequestParam String name, @RequestParam (defaultValue = "0") int offset,
    @RequestParam (defaultValue = "25") int limit){
        
        SearchByNameResult result = gameService.getGamesByName(name, offset, limit);

        return ResponseEntity.ok().body(result);

    }

    @GetMapping("/games/rank")
    public ResponseEntity<SearchByNameResult> getGamesByRank(@RequestParam (defaultValue = "0") int offset,
    @RequestParam (defaultValue = "25") int limit){
        
        SearchByNameResult result = gameService.getGamesByRank(offset, limit);

        return ResponseEntity.ok().body(result);

    }

    @GetMapping("/games/{id}/reviews")
    public ResponseEntity<String> getGameWithReviews(@PathVariable int id){
        String result = gameService.getGamesWithReviews(id);

        return ResponseEntity.ok().body(result);
    }
}
