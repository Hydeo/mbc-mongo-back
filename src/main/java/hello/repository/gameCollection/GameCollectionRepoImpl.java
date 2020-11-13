package hello.repository.gameCollection;

import com.mongodb.client.result.UpdateResult;
import hello.controllers.RequestContract.GameCollectionContract;
import hello.entity.gameCollection.GameCollection;
import hello.service.GameCollectionService;
import org.bson.BsonDocument;
import org.bson.BsonObjectId;
import org.bson.BsonString;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class GameCollectionRepoImpl implements GameCollectionRepoCustom {

    @Autowired
    MongoTemplate mongo_template;

    @Autowired
    GameCollectionService gameCollectionService;

    @Override
    public GameCollection addMaskToGameCollection(GameCollectionContract gcag) {
        //Check if gameId already exist in the collection
        BsonDocument bd = new BsonDocument("userId", new BsonString(gcag.hydrated_token.getUid()));
        bd.append("gameIds", new BsonObjectId(new ObjectId(gcag.gameId)));
        long count = mongo_template.getCollection("gameCollection").countDocuments(bd);

        try {
            BsonDocument bd2 = new BsonDocument("_id", new BsonObjectId(new ObjectId(gcag.gameId)));
            long count1 = mongo_template.getCollection("game").countDocuments(bd2);
            if(count1 ==0) {
                //TODO Throw non existing game error
            }
        }
        catch (IllegalArgumentException e){
            //TODO Handle IllegalArgumentException
            return gameCollectionService.getGameCollectionByUserFirebaseUID(gcag.hydrated_token.getUid());
        }

        if(count > 0 ){
            //Upsert create a new gameCollection doc if it doesn't exist
            Query query = new Query();
            query.addCriteria(new Criteria().where("userId").is(gcag.hydrated_token.getUid()));
            UpdateResult upr = mongo_template.upsert(query, new Update().set("gameMask."+gcag.gameId,gcag.gameMask), GameCollection.class);
        }
        return  gameCollectionService.getGameCollectionByUserFirebaseUID(gcag.hydrated_token.getUid());
    }

}
