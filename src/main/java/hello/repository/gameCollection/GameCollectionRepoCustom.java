package hello.repository.gameCollection;

import hello.controllers.GameCollectionRequest.GameCollectionAddGame;
import hello.entity.gameCollection.GameCollection;

public interface GameCollectionRepoCustom {
    GameCollection addGameToCollection(GameCollectionAddGame gcag);
}
