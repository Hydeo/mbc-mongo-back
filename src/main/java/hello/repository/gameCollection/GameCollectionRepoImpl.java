package hello.repository.gameCollection;

import com.mongodb.client.result.UpdateResult;
import hello.controllers.RequestContract.GameCollectionContract;
import hello.entity.gameCollection.GameCollection;
import hello.entity.gameCollection.GameCollectionFilled;
import org.bson.BsonDocument;
import org.bson.BsonObjectId;
import org.bson.BsonString;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class GameCollectionRepoImpl implements GameCollectionRepoCustom {

    @Autowired
    MongoTemplate mongo_template;

    @Override
    public GameCollection addGameToCollection(GameCollectionContract gcag) {
        //Check if gameId already exist in the collection
        BsonDocument bd = new BsonDocument("userId", new BsonString(gcag.hydrated_token.getUid()));
        bd.append("gameIds", new BsonObjectId(new ObjectId(gcag.gameId)));
        long count = mongo_template.getCollection("gameCollection").count(bd);

        try {
            BsonDocument bd2 = new BsonDocument("_id", new BsonObjectId(new ObjectId(gcag.gameId)));
            long count1 = mongo_template.getCollection("game").count(bd2);
            if(count1 ==0) {
                //TODO Throw non existing game error
            }
        }
        catch (IllegalArgumentException e){
            //TODO Handle IllegalArgumentException
            return null;
        }


        if(count > 0 ){
            //TODO Handle Custom Exeption, here : game already exist in the collection
            return null;
        }

        //Upsert create a new gameCollection doc if it doesn't exist
        Query query = new Query();
        query.addCriteria(new Criteria().where("userId").is(gcag.hydrated_token.getUid()));
        UpdateResult upr = mongo_template.upsert(query, new Update().push("gameIds", new ObjectId(gcag.gameId)), GameCollection.class);

        return null;
    }

    @Override
    public GameCollection addMaskToGameCollection(GameCollectionContract gcag) {
        //Check if gameId already exist in the collection
        BsonDocument bd = new BsonDocument("userId", new BsonString(gcag.hydrated_token.getUid()));
        bd.append("gameIds", new BsonObjectId(new ObjectId(gcag.gameId)));
        long count = mongo_template.getCollection("gameCollection").count(bd);

        try {
            BsonDocument bd2 = new BsonDocument("_id", new BsonObjectId(new ObjectId(gcag.gameId)));
            long count1 = mongo_template.getCollection("game").count(bd2);
            if(count1 ==0) {
                //TODO Throw non existing game error
            }
        }
        catch (IllegalArgumentException e){
            //TODO Handle IllegalArgumentException
            return null;
        }


        if(count > 0 ){
            //Upsert create a new gameCollection doc if it doesn't exist
            Query query = new Query();
            query.addCriteria(new Criteria().where("userId").is(gcag.hydrated_token.getUid()));
            UpdateResult upr = mongo_template.upsert(query, new Update().set("gameMask."+gcag.gameId,gcag.gameMask), GameCollection.class);
            return null;
        }
        return null;
    }

    @Override
    public GameCollectionFilled getUserCollection(String userId) {

        Aggregation agreg = newAggregation(
                match(Criteria.where("userId").is(userId)),
                unwind("gameIds"),
                lookup("game","gameIds","_id","gameList"),
                unwind("gameList"),
                group("_id")
                        .first("userId").as("userId")
                        .push("gameIds").as("gameIds")
                        .push("gameList").as("gameList")
                        .first("gameMask").as("gameMask")
        );

        //Convert the aggregation result into a List
        AggregationResults<GameCollectionFilled> groupResults
                = mongo_template.aggregate(agreg,"gameCollection" , GameCollectionFilled.class);
        List<GameCollectionFilled> result = groupResults.getMappedResults();

        return result.get(0);
    }

    @Override
    public GameCollection removeGameFromCollectionById(GameCollectionContract gcag) {
        //TODO Delete Game mask ?
        Query q = new Query();
        q.addCriteria(Criteria.where("userId").is(gcag.hydrated_token.getUid()));
        Update u =
                new Update().pull("gameIds", new BsonObjectId(new ObjectId("5b55deea1bbc9850a41d6fc1")));
        UpdateResult ur =  mongo_template.updateFirst(q,u,GameCollection.class);
        System.out.println(ur.toString());
        return null;
    }

}
