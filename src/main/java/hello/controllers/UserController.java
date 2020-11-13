package hello.controllers;

import hello.entity.User;
import hello.repository.user.UserRepo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @RequestMapping(method = RequestMethod.GET, value="/getByIdentifier/{identifier}")
    public ResponseEntity<User> getUserByIdentifier(@PathVariable String identifier){
        User u = userRepo.findByFirebaseIdentifier(identifier);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value= "/signUp")
    public ResponseEntity<User> signUpUser(@RequestBody String json){

        //TODO Validate Json to check if valid

        JSONParser jp = new JSONParser();
        try{
            JSONObject jp_parsed = (JSONObject) jp.parse(json);
            User u = new User();
            u.setFirebaseUID(jp_parsed.get("uid").toString());
            u.setFirebaseIdentifier(jp_parsed.get("identifier").toString());
            userRepo.save(u);
            return new ResponseEntity<>(u, HttpStatus.OK);
        }
        catch(ParseException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
