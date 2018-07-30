package hello.entity.gameCollection;

import org.bson.types.ObjectId;

import java.util.List;

public class GameCollection {

    public String userId;
    public List<ObjectId> gameIds;

    public GameCollection() {
    }

    public GameCollection(String userId, List<ObjectId> gameIds) {

        this.userId = userId;
        this.gameIds = gameIds;
    }
    
    public String getuserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }

    public List<ObjectId> getGameIds() {
        return gameIds;
    }

    public void setGameIds(List<ObjectId> gameIds) {
        this.gameIds = gameIds;
    }


}
