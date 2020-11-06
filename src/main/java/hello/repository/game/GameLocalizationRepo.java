package hello.repository.game;

import hello.entity.game.Game;
import hello.entity.game.GameLocalization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameLocalizationRepo extends JpaRepository<GameLocalization,Long>{

}
