package hello.repository.gameCollection;

import hello.controllers.GameCollectionRequest.GameCollectionUserGame;
import hello.entity.gameCollection.GameCollection;
import hello.entity.gameCollection.GameCollectionFilled;

public interface GameCollectionRepoCustom {
    GameCollection addGameToCollection(GameCollectionUserGame gcag);
    GameCollectionFilled getUserCollection(String userId);
    GameCollection removeGameFromCollectionById(GameCollectionUserGame gcag);
}
