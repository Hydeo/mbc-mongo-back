package hello.entity.gameCollection;

import org.bson.types.ObjectId;

import java.util.List;

public class GameCollection {

    public String userId;
    public Boolean isPublic;
    public List<ObjectId> gameIds;
    public List<GameMask> gameMask;

    public GameCollection(String userId, List<ObjectId> gameIds, List<GameMask> gameMask) {
        this.userId = userId;
        this.gameIds = gameIds;
        this.gameMask = gameMask;
    }

    public GameCollection(String userId, List<ObjectId> gameIds, List<GameMask> gameMask,Boolean isPublic) {
        this.userId = userId;
        this.gameIds = gameIds;
        this.gameMask = gameMask;
        this.isPublic = isPublic == null ? false : isPublic;
    }



    public GameCollection() {
    }

    public GameCollection(String userId, List<ObjectId> gameIds) {

        this.userId = userId;
        this.gameIds = gameIds;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
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


    public List<GameMask> getGameMask() {
        return gameMask;
    }

    public void setGameMask(List<GameMask> gameMask) {
        this.gameMask = gameMask;
    }


}
