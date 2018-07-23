package hello.repository.gameCollection;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameCollectionRepo extends MongoRepository<GameCollectionRepo,String>,GameCollectionRepoCustom{
}
