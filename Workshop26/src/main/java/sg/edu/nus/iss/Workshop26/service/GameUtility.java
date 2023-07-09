package sg.edu.nus.iss.Workshop26.service;

import java.util.Date;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.Workshop26.model.Game;
import sg.edu.nus.iss.Workshop26.model.GameSummary;

public class GameUtility {

    public static Game toGame(Document doc){
        Game game = new Game();
        game.setGame_id(doc.getObjectId("_id").toString());
        game.setName(doc.getString("name"));
        game.setYear(doc.getInteger("year"));
        game.setRanking(doc.getInteger("ranking"));
        game.setUsers_rated(doc.getInteger("users_rated"));
        game.setAverage((double) game.getRanking()/ game.getUsers_rated());
        game.setUrl(doc.getString("url"));
        game.setThumbnail(doc.getString("image"));
        game.setTimestamp(new Date());
        return game;
    }

    public static GameSummary toGameSummary(Document doc){
        GameSummary gameSummary = new GameSummary();
        gameSummary.setGame_id(doc.getInteger("gid"));
        gameSummary.setName(doc.getString("name"));
        return gameSummary;
    }

    public static JsonObjectBuilder gameSummaryToJson (Game game) {
        return Json.createObjectBuilder()
            .add("game_id", game.getGame_id())
            .add("name", game.getName());
	}

}
