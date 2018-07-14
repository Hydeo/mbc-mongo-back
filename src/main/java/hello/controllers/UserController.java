package hello.controllers;

import hello.entity.User;
import hello.repository.user.UserRepo;
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
}
