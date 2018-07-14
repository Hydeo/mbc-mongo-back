package hello.controllers;

import hello.entity.User;
import hello.repository.user.UserRepo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @RequestMapping(method = RequestMethod.GET, value="/getByIdentifier/{identifier}")
    public User getUserByIdentifier(@PathVariable String identifier){
        return userRepo.findByIdentifier(identifier);
    }

    @RequestMapping(method = RequestMethod.POST, value= "/signUp")
    public User signUpUser(@RequestBody String json){

        //TODO Validate Json to check if valid

        JSONParser jp = new JSONParser();
        try{
            JSONObject jp_parsed = (JSONObject) jp.parse(json);
            return userRepo.save(new User(jp_parsed.get("uid").toString(), jp_parsed.get("identifier").toString()));
        }
        catch(ParseException e){
            //TODO See what kind of return we should do for errors
            return null;
        }
    }
}
