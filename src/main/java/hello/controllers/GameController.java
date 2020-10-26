package hello.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.firebase.auth.FirebaseAuthException;
import hello.controllers.RequestContract.Contract;
import hello.controllers.RequestContract.GameContract;
import hello.entity.game.Game;
import hello.repository.game.GameRepo;
import hello.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class GameController extends Controller {

    @Autowired
    public GameRepo game_repo;

    @RequestMapping(method = RequestMethod.POST, value="/game")
    public ResponseEntity<Game> addGame(@RequestBody String Json) throws FirebaseAuthException {
        Contract new_game_request = deserialize(Json,"hello.controllers.RequestContract.GameContract");
        Game game_object = ((GameContract)new_game_request).getNew_game();
        //TODO check if everything ok when saving
        game_repo.save(game_object);
        return new ResponseEntity<Game>(game_object, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value="/game")
    public JsonNode getAllGameLibrary(){
        List<Game> game_library = game_repo.findAllGames();
        JsonNode json_game_library = MyUtils.customObjectIdJsonMapper(game_library);
        return json_game_library;
    }

    @RequestMapping(method = RequestMethod.GET, value="/game/{id}")
    public ResponseEntity getGameById(@PathVariable String id){
        Optional<Game> g = game_repo.findById(id);
        if(g.isPresent()){
            JsonNode json_game_library = MyUtils.customObjectIdJsonMapper(g.get());
            return new ResponseEntity<JsonNode>(json_game_library, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Game not found.", HttpStatus.NOT_FOUND);
    }

}
