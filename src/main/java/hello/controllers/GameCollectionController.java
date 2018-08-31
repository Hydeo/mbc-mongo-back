package hello.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import hello.controllers.GameCollectionRequest.UserGameRequestContract;
import hello.entity.gameCollection.GameCollection;
import hello.entity.gameCollection.GameCollectionFilled;
import hello.repository.gameCollection.GameCollectionRepo;
import hello.utils.MyUtils;
import hello.utils.beans.FireBaseCustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
public class GameCollectionController {

    @Autowired
    public GameCollectionRepo game_collection_repo;

    @Autowired
    public FireBaseCustomUtils fcu;

    @RequestMapping(method = RequestMethod.PUT, value="/GameCollection")
    public GameCollection addGameToCollection(@RequestBody String json){
        ObjectMapper om = new ObjectMapper();
        UserGameRequestContract gcag = new UserGameRequestContract();
        try {
            gcag = om.readValue(json,UserGameRequestContract.class);

            if(gcag.validateToken() == true) {
                game_collection_repo.addGameToCollection(gcag);
            }
            else{
                //TODO return error http
            }

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

    @RequestMapping(method = RequestMethod.GET, value="/GameCollection/{token}")
    public JsonNode getGameCollectionByUserID(@PathVariable String userId){

        GameCollectionFilled user_game_collection =  game_collection_repo.getUserCollection(userId);

        JsonNode json_user_game_collection = MyUtils.customObjectIdJsonMapper(user_game_collection);

        return json_user_game_collection;
    }

    @RequestMapping(method = RequestMethod.GET, value="/GameCollection/test/{token}")
    public FirebaseToken test(@PathVariable String userId){


        FirebaseToken ft = fcu.validateToken("eyJhbGciOiJSUzI1NiIsImtpZCI6ImEwY2ViNDY3NDJhNjNlMTk2NDIxNjNhNzI4NmRjZDQyZjc0MzYzNjYifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdGVzdC03ZDNmMSIsIm5hbWUiOiJQIEdQIiwicGljdHVyZSI6Imh0dHBzOi8vbGg2Lmdvb2dsZXVzZXJjb250ZW50LmNvbS8tMTFjeXkweXdXTTAvQUFBQUFBQUFBQUkvQUFBQUFBQUFBQUEvQVBVSUZhTkRzYnJhWHR1akNoeVU1UVQ0OHJ6eTNEcXNZUS9tby9waG90by5qcGciLCJhdWQiOiJ0ZXN0LTdkM2YxIiwiYXV0aF90aW1lIjoxNTM1NzE3NjA2LCJ1c2VyX2lkIjoiN3doWHVtUDFuT1RWUHhFSnJEeHBPS3ZOVG8wMiIsInN1YiI6Ijd3aFh1bVAxbk9UVlB4RUpyRHhwT0t2TlRvMDIiLCJpYXQiOjE1MzU3MTc2MDYsImV4cCI6MTUzNTcyMTIwNiwiZW1haWwiOiJwLmdhcmNpYXBlbGF5b0BnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJnb29nbGUuY29tIjpbIjEwMjUxOTMzOTA5NzExOTkwMTE1MSJdLCJlbWFpbCI6WyJwLmdhcmNpYXBlbGF5b0BnbWFpbC5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.SJqJyCPSBKO_6wry8z8MbBpZnrjXgS5xZT4Mv_HqJIz6LhtzR4SVyMgjr_P9JUW5RqAQmksEdDLogaSs-Ov63XNuiHLd6v8dpSpaaBht1YWmPjEZDTZV3T1vN7LaI7ppthSPLclqJzxGyf1y6OrEBLKnpUeBzfMrML9q9y0y1Duy6vDiBQ6J4t6GtCdAXq6VbPY4bIsqdJZbHPUPUhpQJiEUPs0qbJaRKCG59gAoyLkKsTslFEMd4z1k8ATlg4AOsbiYE3LQHZoGnpivhfIWgPUOoMlZpOuRwb_mLFMdk1MnS_MWO24gL-p90qs55d_Kbk5MF7QHFecUnPCTABWSz");

        System.out.println(ft);

        return ft;

    }
}
