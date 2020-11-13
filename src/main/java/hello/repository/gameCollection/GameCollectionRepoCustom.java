package hello.repository.gameCollection;

import hello.controllers.RequestContract.GameCollectionContract;
import hello.entity.gameCollection.GameCollection;
import hello.entity.gameCollection.GameCollectionFilled;

public interface GameCollectionRepoCustom {
    GameCollectionFilled addMaskToGameCollection(GameCollectionContract gcag);
    GameCollectionFilled getUserCollection(String userId);
}
