package hello.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import hello.entity.Categories.LocalizedCategories;
import hello.repository.categories.CategoriesRepo;
import hello.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CategoriesController extends Controller {

    @Autowired
    CategoriesRepo categories_repo;


    @RequestMapping(method = RequestMethod.GET, value="/categories/{lang}")
    public JsonNode getCategoriesByLang(@PathVariable String lang){
        LocalizedCategories all_localized_categories = categories_repo.findByLang(lang);
        JsonNode json_game_library = MyUtils.customObjectIdJsonMapper(all_localized_categories);
        return json_game_library;
    }
}
