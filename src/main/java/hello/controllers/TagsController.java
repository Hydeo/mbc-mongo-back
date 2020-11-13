package hello.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import hello.entity.tag.Tag;
import hello.repository.tag.TagRepo;
import hello.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class TagsController extends Controller{

    @Autowired
    TagRepo tagRepo;

    @RequestMapping(method = RequestMethod.GET, value="/tag/")
    public ResponseEntity<List<Tag>> getAllTag(){
        List<Tag> tags = tagRepo.findAll();
        return new ResponseEntity<List<Tag>>(tags,HttpStatus.OK);
    }
}
