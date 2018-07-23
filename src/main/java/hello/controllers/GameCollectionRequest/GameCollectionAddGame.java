package hello.controllers.GameCollectionRequest;

public class GameCollectionAddGame {

    public String userId;
    public String gameId;

    public GameCollectionAddGame(String userId, String gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }

    public GameCollectionAddGame() {

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
