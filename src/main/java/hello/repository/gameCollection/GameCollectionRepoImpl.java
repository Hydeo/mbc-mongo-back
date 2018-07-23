package hello.repository.gameCollection;

import com.mongodb.BasicDBObject;
import hello.controllers.GameCollectionRequest.GameCollectionAddGame;
import hello.entity.gameCollection.GameCollection;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;


public class GameCollectionRepoImpl implements GameCollectionRepoCustom {

    @Autowired
    MongoTemplate mongo_template;

    @Override
    public GameCollection addGameToCollection(GameCollectionAddGame gcag) {
        //Check if gameId already exist in the collection
        BsonDocument bd = new BsonDocument("userId", new BsonString(gcag.userId));
        bd.append("gameIds", new BsonString(gcag.gameId));
        long count = mongo_template.getCollection("gameCollection").count(bd);

        if(count > 0 ){
            //TODO Handle Custom Exeption, here : game already exist in the collection
            return null;
        }

        //Upsert create a new gameCollection doc if it doesn't exist
        Query query = new Query();
        query.addCriteria(new Criteria().where("userId").is(gcag.userId));
        mongo_template.upsert(query, new Update().push("gameIds", gcag.gameId), GameCollection.class);

        return null;
    }
}
