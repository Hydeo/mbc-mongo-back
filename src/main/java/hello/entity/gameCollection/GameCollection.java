package hello.entity.gameCollection;

import hello.entity.game.Game;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@Getter
@Setter
public class GameCollection extends AGameCollection{
    @DBRef
    public List<Game> gameIds;
}
