package hello.entity.gameCollection;

import hello.entity.game.Game;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;

@Data
public class GameCollectionFilled {

    public String id;
    public String userId;
    public List<Game> gameList;
    public HashMap<String,GameMask> gameMask;

}
