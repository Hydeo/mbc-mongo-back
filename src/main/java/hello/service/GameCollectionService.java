package hello.service;

import hello.entity.User;
import hello.entity.gameCollection.GameCollection;
import hello.repository.gameCollection.GameCollectionRepo;
import hello.repository.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameCollectionService {

    @Autowired
    UserRepo ur;

    @Autowired
    GameCollectionRepo gcr;

    public GameCollection getGameCollectionByUserFirebaseUID(String firebaseUID) throws Exception {
        User u = ur.findByFirebaseUID(firebaseUID);
        if (u != null) {
            GameCollection gc = gcr.findByUserId(u.getId());
            return gc;
        }
        throw new Exception("User not found");
    }
}
