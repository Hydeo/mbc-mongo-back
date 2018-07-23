package hello.repository.game;

import hello.entity.game.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepo extends MongoRepository<Game,String> {

}
