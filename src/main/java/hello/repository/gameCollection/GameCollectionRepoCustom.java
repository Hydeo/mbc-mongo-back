package hello.repository.gameCollection;

import hello.controllers.RequestContract.GameCollectionContract;
import hello.entity.gameCollection.GameCollection;

public interface GameCollectionRepoCustom {
    GameCollection addMaskToGameCollection(GameCollectionContract gcag);
}
