package hello.entity.game;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

public class Game {

    public ObjectId _id;
    public int nb_player_min;
    public int nb_player_max;
    public String time_to_play;
    public int age_recommended;
    public double complexity;
    public String type;
    public List<String> categories;
    public List<String> mechanism;
    public Map<String,GameLocalization> localization;

    public Game(ObjectId _id, int nb_player_min, int nb_player_max, String time_to_play, int age_recommended, double complexity, String type, List<String> categories, List<String> mechanism, Map<String, GameLocalization> localization) {
        this._id = _id;
        this.nb_player_min = nb_player_min;
        this.nb_player_max = nb_player_max;
        this.time_to_play = time_to_play;
        this.age_recommended = age_recommended;
        this.complexity = complexity;
        this.type = type;
        this.categories = categories;
        this.mechanism = mechanism;
        this.localization = localization;
    }

    public int getNb_player_min() {
        return nb_player_min;
    }

    public void setNb_player_min(int nb_player_min) {
        this.nb_player_min = nb_player_min;
    }

    public int getNb_player_max() {
        return nb_player_max;
    }

    public void setNb_player_max(int nb_player_max) {
        this.nb_player_max = nb_player_max;
    }

    public Game(){
    }

    public Game(int nb_player_min, int nb_player_max, String time_to_play, int age_recommended, double complexity, String type, List<String> categories, List<String> mechanism, Map<String, GameLocalization> localization) {

        this.nb_player_min = nb_player_min;
        this.nb_player_max = nb_player_max;
        this.time_to_play = time_to_play;
        this.age_recommended = age_recommended;
        this.complexity = complexity;
        this.type = type;
        this.categories = categories;
        this.mechanism = mechanism;
        this.localization = localization;
    }

    public String getTime_to_play() {
        return time_to_play;
    }

    public void setTime_to_play(String time_to_play) {
        this.time_to_play = time_to_play;
    }

    public int getAge_recommended() {
        return age_recommended;
    }

    public void setAge_recommended(int age_recommended) {
        this.age_recommended = age_recommended;
    }

    public double getComplexity() {
        return complexity;
    }

    public void setComplexity(float complexity) {
        this.complexity = complexity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getMechanism() {
        return mechanism;
    }

    public void setMechanism(List<String> mechanism) {
        this.mechanism = mechanism;
    }

    public Map<String, GameLocalization> getLocalization() {
        return localization;
    }

    public void setLocalization(Map<String, GameLocalization> localization) {
        this.localization = localization;
    }
}
