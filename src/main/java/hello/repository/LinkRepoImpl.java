package hello.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import org.bson.BasicBSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import hello.entity.Link;

import java.util.ArrayList;
import java.util.List;

public class LinkRepoImpl implements LinkRepoCustom {

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public ArrayList<String> getOwnerList(String owner) {
        Query query = new Query();

        BasicDBObject distinct_criteria = new BasicDBObject();
        distinct_criteria.append("owner",owner);
        DistinctIterable<String> c = mongoTemplate.getCollection("link").distinct("listName",distinct_criteria, String.class);

        ArrayList<String> listNames = new ArrayList<>();
        c.forEach((Block<? super String>) listNames::add);
        for (String doc : c) {
            System.out.println(doc);
        }
        return listNames;
    }
}
