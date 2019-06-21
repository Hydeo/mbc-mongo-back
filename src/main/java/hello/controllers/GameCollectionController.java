package hello.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import hello.controllers.RequestContract.GameCollectionContract;
import hello.entity.gameCollection.GameCollection;
import hello.entity.gameCollection.GameCollectionFilled;
import hello.repository.gameCollection.GameCollectionRepo;
import hello.utils.MyUtils;
import hello.utils.beans.FireBaseCustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class GameCollectionController extends Controller{

    @Autowired
    public GameCollectionRepo game_collection_repo;

    @Autowired
    public FireBaseCustomUtils fcu;

    /*private GameCollectionContract deserialize(String json){
        ObjectMapper om = new ObjectMapper();
        GameCollectionContract gcag = new GameCollectionContract();
        try {
            gcag = om.readValue(json, GameCollectionContract.class);

            if(gcag.validateToken() == true) {
                return gcag;
            }
            else{
                //TODO Throw invalid token then http error
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    @RequestMapping(method = RequestMethod.PUT, value="/GameCollection")
    public ResponseEntity<JsonNode> toogleGameToCollection(@RequestBody String json) throws FirebaseAuthException {
        GameCollectionContract gcc = (GameCollectionContract) deserialize(json,"hello.controllers.RequestContract.GameCollectionContract");
        //if gcc not null
        GameCollectionFilled gc = game_collection_repo.toogleGameToCollection(gcc);
        //TODO Get the tnew collection and send it back
        JsonNode json_user_game_collection = MyUtils.customObjectIdJsonMapper(gc);
        return new ResponseEntity<JsonNode>(json_user_game_collection, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/GameCollection/mask")
    public ResponseEntity<GameCollectionFilled> addMaskToGameCollection(@RequestBody String json) throws FirebaseAuthException {
        GameCollectionContract gcc = (GameCollectionContract) deserialize(json,"hello.controllers.RequestContract.GameCollectionContract");
        //if gcc not null
        GameCollectionFilled gc = game_collection_repo.addMaskToGameCollection(gcc);
        return new ResponseEntity<GameCollectionFilled>(gc, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, value="/GameCollection")
    public JsonNode getGameCollectionByUserID(@RequestBody String json) throws FirebaseAuthException {
        GameCollectionContract gcc = (GameCollectionContract) deserialize(json,"hello.controllers.RequestContract.GameCollectionContract");
        //if gcc not null
        GameCollectionFilled user_game_collection =  game_collection_repo.getUserCollection(gcc.hydrated_token.getUid());
        JsonNode json_user_game_collection = MyUtils.customObjectIdJsonMapper(user_game_collection);
        return json_user_game_collection;
    }



    @RequestMapping(method = RequestMethod.GET, value="/GameCollection/test/{token}")
    public FirebaseToken test(@PathVariable String userId){
        return null;
    }

}
