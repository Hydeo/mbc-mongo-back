package hello.entity.gameCollection;

import hello.entity.game.Game;
import org.bson.types.ObjectId;

import java.util.List;

public class GameCollectionFilled {

    public String id;
    public List<ObjectId> gameIds;
    public List<Game> gameList;

    public GameCollectionFilled(String id, List<ObjectId> gameIds, List<Game> gameList) {
        this.id = id;
        this.gameIds = gameIds;
        this.gameList = gameList;
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
