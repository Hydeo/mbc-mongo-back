package hello.repository.gameCollection;

import hello.controllers.RequestContract.GameCollectionContract;
import hello.entity.gameCollection.GameCollectionFilled;

public interface GameCollectionRepoCustom {
    GameCollectionFilled addGameToCollection(GameCollectionContract gcag);
    GameCollectionFilled addMaskToGameCollection(GameCollectionContract gcag);
    GameCollectionFilled getUserCollection(String userId);
    void removeGameFromCollectionById(GameCollectionContract gcag);
}
