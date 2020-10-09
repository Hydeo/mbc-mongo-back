package hello.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import hello.controllers.RequestContract.Contract;
import hello.controllers.RequestContract.GameCollectionContract;
import hello.entity.gameCollection.GameCollection;
import hello.entity.gameCollection.GameCollectionFilled;
import hello.repository.game.GameRepo;
import hello.repository.gameCollection.GameCollectionRepo;
import hello.utils.MyUtils;
import hello.utils.beans.FireBaseCustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class GameCollectionController extends Controller{

    @Autowired
    public GameCollectionRepo game_collection_repo;

    @Autowired
    public GameRepo game_repo;

    @Autowired
    public FireBaseCustomUtils fcu;

    @RequestMapping(method = RequestMethod.PUT, value="/GameCollection")
    public ResponseEntity<JsonNode> toogleGameToCollection(@RequestBody String json) throws FirebaseAuthException {
        GameCollectionContract gcc = (GameCollectionContract) deserialize(json,"hello.controllers.RequestContract.GameCollectionContract");
        //if gcc not null
        GameCollectionFilled gc = game_collection_repo.toogleGameToCollection(gcc);
        //TODO Get the tnew collection and send it back
        JsonNode json_user_game_collection = MyUtils.customObjectIdJsonMapper(gc);
        return new ResponseEntity<JsonNode>(json_user_game_collection, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/GameCollection/privacy")
    public ResponseEntity<Void> toogleIsPublicCollection(@RequestBody String json) throws FirebaseAuthException {
        Contract c = deserialize(json,"hello.controllers.RequestContract.Contract");
        //if gcc not null
        GameCollection gc = game_collection_repo.findByUserId(c.getHydrated_token().getUid());
        if(gc != null) {
            game_collection_repo.updateIsPublicCollection(!gc.isPublic, gc.id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/GameCollection/mask")
    public ResponseEntity addMaskToGameCollection(@RequestBody String json) throws FirebaseAuthException {
        GameCollectionContract gcc = (GameCollectionContract) deserialize(json,"hello.controllers.RequestContract.GameCollectionContract");

        Boolean gameExist = game_repo.existsById(gcc.gameId);
        if(gameExist) {
            GameCollectionFilled gc = game_collection_repo.addMaskToGameCollection(gcc);
            return new ResponseEntity<GameCollectionFilled>(gc, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Game doesn't exist", HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, value="/GameCollection")
    public JsonNode getGameCollectionByUserID(@RequestBody String json) throws FirebaseAuthException {
        Contract c = (Contract) deserialize(json,"hello.controllers.RequestContract.Contract");

        GameCollectionFilled user_game_collection =  game_collection_repo.getUserCollection(c.hydrated_token.getUid());
        JsonNode json_user_game_collection = MyUtils.customObjectIdJsonMapper(user_game_collection);

        return json_user_game_collection;
    }

    @RequestMapping(method = RequestMethod.GET, value="/GameCollection/{uid}")
    public ResponseEntity getPublicGameCollectionByUserID(@PathVariable String uid) {
        GameCollectionFilled user_game_collection =  game_collection_repo.getUserCollection(uid);
        if(user_game_collection.isPublic) {
            JsonNode json_user_game_collection = MyUtils.customObjectIdJsonMapper(user_game_collection);
            return new ResponseEntity<JsonNode>(json_user_game_collection,HttpStatus.OK);
        }
        return new ResponseEntity<String>("Game Collection doesn't exist or is not public.", HttpStatus.FORBIDDEN);
    }


    @RequestMapping(method = RequestMethod.GET, value="/GameCollection/test/{token}")
    public FirebaseToken test(@PathVariable String userId){
        return null;
    }

}
