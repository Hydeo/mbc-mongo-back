package hello.entity.gameCollection;

import org.bson.types.ObjectId;

import java.util.ArrayList;


public class GameCollection {

    public String id;
    public String userId;
    public Boolean isPublic;
    public ArrayList<ObjectId> gameIds;
    public ArrayList<GameMask> gameMask;

    public GameCollection(String userId, ArrayList<ObjectId> gameIds, ArrayList<GameMask> gameMask) {
        this.userId = userId;
        this.gameIds = gameIds;
        this.gameMask = gameMask;
    }

    public GameCollection(String userId, ArrayList<ObjectId> gameIds, ArrayList<GameMask> gameMask,Boolean isPublic) {
        this.userId = userId;
        this.gameIds = gameIds;
        this.gameMask = gameMask;
        this.isPublic = isPublic == null ? false : isPublic;
    }

    public GameCollection() {
    }

    public GameCollection(String userId, ArrayList<ObjectId> gameIds) {

        this.userId = userId;
        this.gameIds = gameIds;
    }

    public GameCollection(String id, String userId, Boolean isPublic, ArrayList<ObjectId> gameIds, ArrayList<GameMask> gameMask) {
        this.id = id;
        this.userId = userId;
        this.isPublic = isPublic;
        this.gameIds = gameIds;
        this.gameMask = gameMask;
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

    public ArrayList<ObjectId> getGameIds() {
        return gameIds;
    }

    public void setGameIds(ArrayList<ObjectId> gameIds) {
        this.gameIds = gameIds;
    }


    public ArrayList<GameMask> getGameMask() {
        return gameMask;
    }

    public void setGameMask(ArrayList<GameMask> gameMask) {
        this.gameMask = gameMask;
    }


}
