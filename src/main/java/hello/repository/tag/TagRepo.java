package hello.repository.tag;

import hello.entity.tag.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.lang.reflect.Array;
import java.util.List;

public interface TagRepo extends MongoRepository<Tag,String>,TagRepoCustom {
    Tag findByTagName(String tag_name);
}
