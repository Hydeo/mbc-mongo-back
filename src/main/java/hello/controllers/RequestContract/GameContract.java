package hello.controllers.RequestContract;

import hello.entity.game.Game;
import hello.utils.beans.FireBaseCustomUtils;

public class GameContract extends Contract{

    Game new_game;

    public GameContract(FireBaseCustomUtils fcu, String token, Game new_game) {
        super(token);
        this.new_game = new_game;
    }

    public GameContract() {

    }

    public Game getNew_game() {
        return new_game;
    }

    public void setNew_game(Game new_game) {
        this.new_game = new_game;
    }
}
