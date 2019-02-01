package hello.entity.Categories;

import hello.entity.gameCollection.GameMask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LocalizedCategories {

    String lang;
    HashMap<String,Categories> categories;

    public LocalizedCategories(String lang, HashMap<String, Categories> categories) {
        this.lang = lang;
        this.categories = categories;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public HashMap<String, Categories> getCategories() {
        return categories;
    }

    public void setCategories(HashMap<String, Categories> categories) {
        this.categories = categories;
    }
}
