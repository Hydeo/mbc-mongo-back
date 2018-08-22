package hello.entity.gameCollection;

import hello.entity.game.Game;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;

public class GameCollectionFilled {

    public String id;
    public String userId;
    public List<ObjectId> gameIds;
    public List<Game> gameList;
    public HashMap<String,GameMask> gameMask;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public GameCollectionFilled(String id, String userId, List<ObjectId> gameIds, List<Game> gameList,HashMap<String,GameMask> gameMask) {
        this.id = id;
        this.userId = userId;
        this.gameIds = gameIds;
        this.gameList = gameList;
        this.gameMask = gameMask;
    }

    public GameCollectionFilled() {

    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ObjectId> getGameIds() {
        return gameIds;
    }

    public void setGameIds(List<ObjectId> gameIds) {
        this.gameIds = gameIds;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }
}
