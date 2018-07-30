package hello.repository.gameCollection;

import hello.entity.gameCollection.GameCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameCollectionRepo extends MongoRepository<GameCollection,String>,GameCollectionRepoCustom{
    GameCollection findByUserId(String userId);
}
