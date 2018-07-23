package hello.entity.gameCollection;

import java.util.List;

public class GameCollection {

    public String userId;
    public List<String> gameIds;

    public GameCollection() {
    }

    public GameCollection(String userId, List<String> gameIds) {

        this.userId = userId;
        this.gameIds = gameIds;
    }
    
    public String getuserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }

    public List<String> getGameIds() {
        return gameIds;
    }

    public void setGameIds(List<String> gameIds) {
        this.gameIds = gameIds;
    }


}
