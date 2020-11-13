package hello.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import hello.controllers.RequestContract.Contract;
import hello.controllers.RequestContract.GameCollectionContract;
import hello.entity.User;
import hello.entity.game.Game;
import hello.entity.gameCollection.GameCollection;
import hello.entity.gameCollection.GameCollectionFilled;
import hello.repository.game.GameRepo;
import hello.repository.gameCollection.GameCollectionRepo;
import hello.repository.user.UserRepo;
import hello.service.GameCollectionService;
import hello.utils.MyUtils;
import hello.utils.beans.FireBaseCustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
@CrossOrigin
public class GameCollectionController extends Controller {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public GameCollectionRepo game_collection_repo;
    @Autowired
    public GameRepo game_repo;
    @Autowired
    public UserRepo ur;

    @Autowired
    public GameCollectionService gameCollectionService;

    @Autowired
    public FireBaseCustomUtils fcu;

    @RequestMapping(method = RequestMethod.PUT, value = "/GameCollection")
    @Transactional
    public ResponseEntity<GameCollection> toogleGameUserCollection(@RequestBody String json) throws FirebaseAuthException {
        GameCollectionContract gcc = (GameCollectionContract) deserialize(json, "hello.controllers.RequestContract.GameCollectionContract");

        User currentUser = ur.findByFirebaseUID(gcc.hydrated_token.getUid());
        GameCollection userGameCollection = game_collection_repo.findByUserId(currentUser.getId());
        Optional<Game> gameToToggle = game_repo.findById(Long.parseLong(gcc.gameId));

        if (gameToToggle.isPresent()) {
            if (userGameCollection.getGames().contains(gameToToggle.get())) {
                userGameCollection.getGames().remove(gameToToggle.get());
            } else {
                userGameCollection.getGames().add(gameToToggle.get());
            }
            entityManager.merge(userGameCollection);
        } else {
            return new ResponseEntity<GameCollection>(userGameCollection, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<GameCollection>(userGameCollection, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/GameCollection/privacy")
    public ResponseEntity<GameCollection> toogleIsPublicUserCollection(@RequestBody String json) throws FirebaseAuthException {
        try {
            Contract c = deserialize(json, "hello.controllers.RequestContract.Contract");
            GameCollection gc = gameCollectionService.getGameCollectionByUserFirebaseUID(c.getHydrated_token().getUid());
            if (gc != null) {
                gc.setIsPublic(!gc.getIsPublic());
                game_collection_repo.save(gc);
                return new ResponseEntity<GameCollection>(gc,HttpStatus.OK);
            }
            return new ResponseEntity<GameCollection>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/GameCollection/mask")
    public ResponseEntity addMaskToGameCollection(@RequestBody String json) throws FirebaseAuthException {
        GameCollectionContract gcc = (GameCollectionContract) deserialize(json, "hello.controllers.RequestContract.GameCollectionContract");

        Boolean gameExist = game_repo.existsById(Long.parseLong(gcc.gameId));
        if (gameExist) {
            GameCollectionFilled gc = game_collection_repo.addMaskToGameCollection(gcc);
            return new ResponseEntity<GameCollectionFilled>(gc, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Game doesn't exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/GameCollection")
    public ResponseEntity<GameCollection> getGameCollectionByUserID(@RequestBody String json) throws FirebaseAuthException {

        Contract c = (Contract) deserialize(json, "hello.controllers.RequestContract.Contract");

        String uid = c.hydrated_token.getUid();
        User u = ur.findByFirebaseUID(c.hydrated_token.getUid());
        GameCollection gc = game_collection_repo.findByUserId(u.getId());
        return new ResponseEntity<GameCollection>(gc, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/GameCollection/{uid}")
    public ResponseEntity getPublicGameCollectionByUserID(@PathVariable String uid) {
        GameCollectionFilled user_game_collection = game_collection_repo.getUserCollection(uid);
        if (user_game_collection.isPublic) {
            JsonNode json_user_game_collection = MyUtils.customObjectIdJsonMapper(user_game_collection);
            return new ResponseEntity<JsonNode>(json_user_game_collection, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Game Collection doesn't exist or is not public.", HttpStatus.FORBIDDEN);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/GameCollection/test/{token}")
    public FirebaseToken test(@PathVariable String userId) {
        return null;
    }

}
