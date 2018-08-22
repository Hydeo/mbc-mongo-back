package hello.repository.gameCollection;

import hello.controllers.GameCollectionRequest.UserGameRequestContract;
import hello.entity.gameCollection.GameCollection;
import hello.entity.gameCollection.GameCollectionFilled;

public interface GameCollectionRepoCustom {
    GameCollection addGameToCollection(UserGameRequestContract gcag);
    GameCollection addMaskToGameCollection(UserGameRequestContract gcag);
    GameCollectionFilled getUserCollection(String userId);
    GameCollection removeGameFromCollectionById(UserGameRequestContract gcag);
}
