package hello.entity.gameCollection;

import hello.entity.game.Game;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;

@Data
@Getter
@Setter
public class GameCollectionFilled extends AGameCollection{

    public List<Game> gameList;
}
