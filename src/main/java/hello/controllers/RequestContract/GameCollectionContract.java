package hello.controllers.RequestContract;

import com.google.firebase.auth.FirebaseToken;
import hello.entity.gameCollection.GameMask;
import hello.utils.SpringContext;
import hello.utils.beans.FireBaseCustomUtils;

public class GameCollectionContract {


    protected FireBaseCustomUtils fcu;

    public String token;
    public String gameId;
    public GameMask gameMask;
    public FirebaseToken hydrated_token;

    public GameCollectionContract(String token, String gameId, GameMask gameMask, FirebaseToken hydrated_token) {
        this.fcu = (FireBaseCustomUtils)SpringContext.getAppContext().getBean("fireBaseCustomUtils");
        this.token = token;
        this.gameId = gameId;
        this.gameMask = gameMask;
        this.hydrated_token = hydrated_token;
    }

    public GameCollectionContract(String token, String gameId, GameMask gameMask) {
        this.fcu = (FireBaseCustomUtils)SpringContext.getAppContext().getBean("fireBaseCustomUtils");
        this.token = token;
        this.gameId = gameId;
        this.gameMask = gameMask;
        this.hydrated_token = null;
    }

    public GameCollectionContract(String token, String gameId) {
        this.fcu = (FireBaseCustomUtils)SpringContext.getAppContext().getBean("fireBaseCustomUtils");
        this.token = token;
        this.gameId = gameId;
        this.hydrated_token = null;
    }

    public GameCollectionContract(String token) {
        this.token = token;
    }

    public GameCollectionContract() {
        this.fcu = (FireBaseCustomUtils)SpringContext.getAppContext().getBean("fireBaseCustomUtils");
    }

    public boolean validateToken(){
        FirebaseToken ft = fcu.validateToken(this.token);
        if(ft == null)
            return false;
        this.hydrated_token = ft;
        return true;

    }

    public GameMask getGameMask() {
        return gameMask;
    }

    public void setGameMask(GameMask gameMask) {
        this.gameMask = gameMask;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
