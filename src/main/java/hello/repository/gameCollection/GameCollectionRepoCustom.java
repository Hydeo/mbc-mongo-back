package hello.repository.gameCollection;

import hello.controllers.GameCollectionRequest.GameCollectionAddGame;
import hello.entity.gameCollection.GameCollection;
import hello.entity.gameCollection.GameCollectionFilled;

public interface GameCollectionRepoCustom {
    GameCollection addGameToCollection(GameCollectionAddGame gcag);

    GameCollectionFilled getUserCollection(String userId);
}
