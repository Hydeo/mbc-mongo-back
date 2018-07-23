package hello.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.controllers.GameCollectionRequest.GameCollectionAddGame;
import hello.entity.gameCollection.GameCollection;
import hello.repository.gameCollection.GameCollectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
public class GameCollectionController {

    @Autowired
    public GameCollectionRepo game_collection_repo;


    @RequestMapping(method = RequestMethod.PUT, value="/GameCollectionRepo")
    public GameCollection addGameToCollection(@RequestBody String json){
        ObjectMapper om = new ObjectMapper();
        GameCollectionAddGame gcag = new GameCollectionAddGame();
        try {
            gcag = om.readValue(json,GameCollectionAddGame.class);
            game_collection_repo.addGameToCollection(gcag);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
