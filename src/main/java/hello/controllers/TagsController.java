package hello.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import hello.entity.tag.Tag;
import hello.repository.tag.TagRepo;
import hello.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TagsController extends Controller{

    @Autowired
    TagRepo tag_repo;

    @RequestMapping(method = RequestMethod.GET, value="/tag/{tagName}")
    public JsonNode getTagByName(@PathVariable String tagName){
        Tag tag = tag_repo.findByTagName(tagName);
        JsonNode j_tag = MyUtils.customObjectIdJsonMapper(tag);
        return j_tag;
    }

    @RequestMapping(method = RequestMethod.GET, value="/tag/")
    public JsonNode getAllTag(){
        List<Tag> list_tag = tag_repo.findAll();
        JsonNode j_list_tag = MyUtils.customObjectIdJsonMapper(list_tag );
        return j_list_tag ;
    }
}
