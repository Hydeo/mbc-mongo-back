package hello.repository.tag;

import hello.entity.tag.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagRepo extends MongoRepository<Tag,String> {
    Tag findByTagName(String tag_name);
}
