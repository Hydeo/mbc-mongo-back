package hello.repository.user;

import hello.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends JpaRepository<User,String> {
    User findByFirebaseIdentifier(String firebaseIdentifier);
    User findByFirebaseUID(String firebaseUID);
}
