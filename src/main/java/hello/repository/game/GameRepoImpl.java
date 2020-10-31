package hello.repository.game;

import hello.entity.game.Game;
import hello.entity.gameCollection.GameCollectionFilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

public class GameRepoImpl implements GameRepoCustom {

    /*@Autowired
    MongoTemplate mongo_template;*/

    @Override
    public List<Game> findAllGames() {


        /*Aggregation agreg = newAggregation(
                unwind("tags"),
                lookup("tag","tags._id","_id","tags"),
                unwind("tags"),
                group("_id")
                        .first("nb_player_min").as("nb_player_min")
                        .first("nb_player_max").as("nb_player_max")
                        .first("time_to_play_min").as("time_to_play_min")
                        .first("time_to_play_max").as("time_to_play_max")
                        .first("complexity").as("complexity")
                        .first("age_recommended").as("age_recommended")
                        .first("type").as("type")
                        .push("tags").as("tags")
                        .first("localization").as("localization")
        );
        AggregationResults<Game> groupResults
                = mongo_template.aggregate(agreg,"game" , Game.class);
        List<Game> result = groupResults.getMappedResults();*/
        return null;
    }
}
