package hello.controllers.GameCollectionRequest;

public class GameCollectionUserGame {

    public String userId;
    public String gameId;

    public GameCollectionUserGame(String userId, String gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }

    public GameCollectionUserGame() {

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
