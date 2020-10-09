package hello.repository.gameCollection;

import hello.controllers.RequestContract.GameCollectionContract;
import hello.entity.gameCollection.GameCollectionFilled;

public interface GameCollectionRepoCustom {
    GameCollectionFilled toogleGameToCollection(GameCollectionContract gcag);
    void updateIsPublicCollection(boolean isPublic, String collectionId );
    GameCollectionFilled addMaskToGameCollection(GameCollectionContract gcag);
    GameCollectionFilled getUserCollection(String userId);

}
