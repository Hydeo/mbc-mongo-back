package hello.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.controllers.GameCollectionRequest.UserGameRequestContract;
import hello.entity.gameCollection.GameCollection;
import hello.entity.gameCollection.GameCollectionFilled;
import hello.repository.gameCollection.GameCollectionRepo;
import hello.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
public class GameCollectionController {

    @Autowired
    public GameCollectionRepo game_collection_repo;


    @RequestMapping(method = RequestMethod.PUT, value="/GameCollection")
    public GameCollection addGameToCollection(@RequestBody String json){
        ObjectMapper om = new ObjectMapper();
        UserGameRequestContract gcag = new UserGameRequestContract();
        try {
            gcag = om.readValue(json,UserGameRequestContract.class);
            game_collection_repo.addGameToCollection(gcag);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT, value="/GameCollection/mask")
    public GameCollection addMaskToGameCollection(@RequestBody String json){
        ObjectMapper om = new ObjectMapper();
        UserGameRequestContract gcag = new UserGameRequestContract();
        try {
            gcag = om.readValue(json,UserGameRequestContract.class);
            game_collection_repo.addMaskToGameCollection(gcag);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE, value ="/GameCollection")
    public GameCollection removeGameFromCollection(@RequestBody String json){
        ObjectMapper om = new ObjectMapper();
        UserGameRequestContract gcag = new UserGameRequestContract();
        try {
            gcag = om.readValue(json,UserGameRequestContract.class);
            game_collection_repo.removeGameFromCollectionById(gcag);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value="/GameCollection/{userId}")
    public JsonNode getGameCollectionByUserID(@PathVariable String userId){

        GameCollectionFilled user_game_collection =  game_collection_repo.getUserCollection(userId);

        JsonNode json_user_game_collection = MyUtils.customObjectIdJsonMapper(user_game_collection);

        return json_user_game_collection;
    }
}
