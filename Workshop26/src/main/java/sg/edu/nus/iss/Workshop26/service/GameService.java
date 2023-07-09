package sg.edu.nus.iss.Workshop26.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.Workshop26.exception.ResourceNotFoundException;
import sg.edu.nus.iss.Workshop26.model.Game;
import sg.edu.nus.iss.Workshop26.model.GameSearchResult;
import sg.edu.nus.iss.Workshop26.model.GameSummary;
import sg.edu.nus.iss.Workshop26.repository.GameRepo;

@Service
public class GameService {
    
    @Autowired
    private GameRepo gameRepo;

    public JsonObject getGamestoJson(String name, int offset, int limit){
        List<Game> gamesList = gameRepo.getGames(name, offset, limit).stream()
            .map(GameUtility :: toGame)
            .toList();

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        List<JsonObjectBuilder> games = gamesList.stream()
            .map(GameUtility :: gameSummaryToJson)
            .toList();

        for (JsonObjectBuilder g : games){
            arrBuilder.add(g);
        }

        return Json.createObjectBuilder()
            .add("games", arrBuilder)
            .add("offset", offset)
            .add("limit", limit)
            .add("total", games.size())
            .add("timestamp", new Date().toString())
            .build();

    }

    public GameSearchResult getGames(String name, int offset, int limit){

        List<GameSummary> gamesList = gameRepo.getGames(name, offset, limit).stream()
            .map(GameUtility :: toGameSummary)
            .toList();
        
        GameSearchResult result = new GameSearchResult();
        result.setGames(gamesList);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setTotal(gamesList.size());
        result.setTimestamp(new Date());

        return result;

    }

    public GameSearchResult getGamesByRank(int offset, int limit){

        List<GameSummary> gamesList = gameRepo.getGamesByRank(offset, limit).stream()
            .map(GameUtility :: toGameSummary)
            .toList();
        
        GameSearchResult result = new GameSearchResult();
        result.setGames(gamesList);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setTotal(gamesList.size());
        result.setTimestamp(new Date());

        return result;

    }

    public Game getGameById(String id){
        Optional<Document> doc = gameRepo.getGameById(id);

        if (doc == null){
            throw new ResourceNotFoundException("Cannot find game");
        }
        
        Game game = GameUtility.toGame(doc.get());
        return game;
    }

}
