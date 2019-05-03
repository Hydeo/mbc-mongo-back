package hello.repository.game;

import hello.entity.game.Game;
import hello.entity.gameCollection.GameCollectionFilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

public class GameRepoImpl implements GameRepoCustom {

    @Autowired
    MongoTemplate mongo_template;

    @Override
    public List<Game> findAllGames() {
        Aggregation agreg = newAggregation(
                lookup("tag","tags","_id","tags")
        );
        AggregationResults<Game> groupResults
                = mongo_template.aggregate(agreg,"game" , Game.class);
        List<Game> result = groupResults.getMappedResults();
        return result;
    }
}
