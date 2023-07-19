package sg.edu.nus.iss.Workshop27.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.Workshop27.model.Game;
import sg.edu.nus.iss.Workshop27.model.GameSummary;
import sg.edu.nus.iss.Workshop27.model.SearchByNameResult;
import sg.edu.nus.iss.Workshop27.repository.GameRepo;

@Service
public class GameService {
    
    @Autowired
    private GameRepo gameRepo;
    
    public SearchByNameResult getGamesByName(String name, int offset, int limit){

        SearchByNameResult result = new SearchByNameResult();

        List<GameSummary> games = gameRepo.getGamesByName(name, limit, offset)
            .stream()
            .map(Utility :: toGameSummary)
            .toList();

        result.setGames(games);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setTotal(games.size());
        result.setTimestamp(new Date());
        
        return result;

    }

    public SearchByNameResult getGamesByRank(int offset, int limit){

        SearchByNameResult result = new SearchByNameResult();

        List<GameSummary> games = gameRepo.getGamesByRank(offset, limit)
            .stream()
            .map(Utility :: toGameSummary)
            .toList();

        result.setGames(games);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setTotal(games.size());
        result.setTimestamp(new Date());

        return result;
    }

    public Optional<Game> getGameById(Integer id){
        
        Optional<Document> doc = gameRepo.getGameById(id);

        if (doc.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(Utility.toGame(doc.get()));
    }

    public String getGamesWithReviews(int id){

        return gameRepo.getGameWithReviews(id).get().toJson();

    }
}
