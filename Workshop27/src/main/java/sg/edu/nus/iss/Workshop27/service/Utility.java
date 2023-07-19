package sg.edu.nus.iss.Workshop27.service;

import org.bson.Document;

import sg.edu.nus.iss.Workshop27.model.Game;
import sg.edu.nus.iss.Workshop27.model.GameSummary;

public class Utility {

    public static GameSummary toGameSummary(Document doc){

        GameSummary game = new GameSummary();
        game.setGame_id(doc.getInteger("gid"));
        game.setName(doc.getString("name"));
        return game;
    }
    
    public static Game toGame(Document doc){

        Game game = new Game();
        game.setGame_id(doc.getInteger("gid"));
        game.setName(doc.getString("name"));
        game.setYear(doc.getInteger("year"));
        game.setRanking(doc.getInteger("ranking"));
        game.setUsers_rated(doc.getInteger("users_rated"));
        game.setUrl(doc.getString("url"));
        game.setThumbnail(doc.getString("image"));
        return game;
        
    }
}
