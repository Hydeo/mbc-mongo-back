package hello.entity.game;

import hello.entity.tag.Tag;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class Game {
    @Id
    public ObjectId _id;

    public int nb_player_min;
    public int nb_player_max;
    public int time_to_play_min;
    public int time_to_play_max;
    public int age_recommended;
    public double complexity;
    public String type;

    public ArrayList<Tag> tags;
    public Map<String,GameLocalization> localization;

}
