package hello.controllers.GameCollectionRequest;

import hello.entity.gameCollection.GameMask;

public class UserGameRequestContract {

    public String userId;
    public String gameId;
    public GameMask gameMask;

    public UserGameRequestContract(String userId, String gameId, GameMask gameMask) {
        this.userId = userId;
        this.gameId = gameId;
        this.gameMask = gameMask;
    }

    public UserGameRequestContract(String userId, String gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }

    public UserGameRequestContract() {

    }

    public GameMask getGameMask() {
        return gameMask;
    }

    public void setGameMask(GameMask gameMask) {
        this.gameMask = gameMask;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
