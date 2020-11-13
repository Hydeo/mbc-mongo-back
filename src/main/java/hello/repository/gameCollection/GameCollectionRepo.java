package hello.repository.gameCollection;

import hello.entity.gameCollection.GameCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameCollectionRepo extends JpaRepository<GameCollection,Long>,GameCollectionRepoCustom{
    GameCollection findByUserId(Long userId);
}
