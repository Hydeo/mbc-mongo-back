package hello.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import hello.entity.tag.Tag;
import hello.repository.tag.TagRepo;
import hello.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
        List<Tag> listTag = tag_repo.findAll();
        HashMap<String,Tag> tagsHashMap = new HashMap<String,Tag>();

        for(Tag t : listTag){
            tagsHashMap.put(t._id.toString(),t);
        }
        JsonNode j_list_tag = MyUtils.customObjectIdJsonMapper(tagsHashMap);
        return j_list_tag ;
    }
}
