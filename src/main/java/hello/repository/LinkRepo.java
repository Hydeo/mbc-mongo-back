package hello.repository;

import hello.entity.Link;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LinkRepo extends MongoRepository<Link,String>, LinkRepoCustom{
     Link findByTitle(String title);
     List<Link> findByOwner(String owner);
     List<Link> findByListName(String listName);
     void deleteById(String id);
     //List<Link> findByListName(String listName);
     //List<String> findListOfOwner(String owner);
}
