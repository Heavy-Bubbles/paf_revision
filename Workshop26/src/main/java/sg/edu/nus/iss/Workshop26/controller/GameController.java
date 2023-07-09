package sg.edu.nus.iss.Workshop26.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.Workshop26.model.Game;
import sg.edu.nus.iss.Workshop26.model.GameSearchResult;
import sg.edu.nus.iss.Workshop26.service.GameService;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    GameService gameService;

    // @GetMapping
    // public ResponseEntity<String> getGamestoJsonString(@RequestParam(required = true) String name,
    // @RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "25") int limit){
    //     return ResponseEntity.ok().body(gameService.getGamestoJson(name, offset, limit).toString());
    // }

    @GetMapping
    public ResponseEntity<GameSearchResult> getGames(@RequestParam(required = true) String name,
    @RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "25") int limit){
        GameSearchResult result = gameService.getGames(name, offset, limit);

        if (result.getGames().size() <= 0){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/rank")
    public ResponseEntity<GameSearchResult> getGamesByRank(@RequestParam(defaultValue = "0") int offset,
    @RequestParam(defaultValue = "25") int limit){
        GameSearchResult result = gameService.getGamesByRank(offset, limit);

        if (result.getGames().size() <= 0){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable String id){
        Game game = gameService.getGameById(id);

        return ResponseEntity.ok().body(game);
    }
    
}
