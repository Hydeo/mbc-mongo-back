package hello.repository.gameCollection;

import hello.controllers.RequestContract.GameCollectionContract;
import hello.entity.gameCollection.GameCollectionFilled;
import org.bson.types.ObjectId;

public interface GameCollectionRepoCustom {
    GameCollectionFilled toogleGameToCollection(GameCollectionContract gcag);
    void updateIsPublicCollection(boolean isPublic, ObjectId collectionId);
    GameCollectionFilled addMaskToGameCollection(GameCollectionContract gcag);
    GameCollectionFilled getUserCollection(String userId);

}
