package hello.repository.game;

import hello.entity.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GameRepo extends JpaRepository<Game,String>, GameRepoCustom{

}
