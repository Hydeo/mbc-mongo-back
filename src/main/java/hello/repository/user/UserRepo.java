package hello.repository.user;

import hello.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String> {
    User findByIdentifier(String identifier);
}
