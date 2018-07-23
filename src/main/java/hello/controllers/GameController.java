package hello.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.entity.game.Game;
import hello.entity.game.GameLocalization;
import hello.repository.game.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class GameController {

    @Autowired
    public GameRepo game_repo;


    @RequestMapping(method = RequestMethod.POST, value="/game")
    public Game addGame(@RequestBody String Json){
        ObjectMapper om = new ObjectMapper();
        Game game_to_add = null;

        //TODO check if everything ok
        try {
            game_to_add = om.readValue(Json,Game.class);
            game_repo.save(game_to_add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return game_to_add;

       /* List<String> cat = new ArrayList<>();
        List<String> mech = new ArrayList<>();
        Map<String,GameLocalization> loc = new HashMap<String,GameLocalization>();
        cat.add("Strat");
        cat.add("Coop");
        mech.add("Dice");
        mech.add("Diplomatie");
        mech.add("bitchy");
        loc.put("eng",new GameLocalization("Risk","Risk is a game of strategy base on trechery and smart moves."));
        Game lol = new Game(1,2,"45",10,3.5,"-1",cat,mech,loc);
        ObjectMapper om = new ObjectMapper();
        try {
            String serelize = om.writeValueAsString(lol);
            System.out.println(serelize);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new Game(1,2,"45",10,3.5,"-1",cat,mech,loc);*/

    }

}
