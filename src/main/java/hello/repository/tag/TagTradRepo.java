package hello.repository.tag;

import hello.entity.tag.Tag;
import hello.entity.tag.TagTrad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagTradRepo extends JpaRepository<TagTrad,String>,TagRepoCustom {
    //Tag findByTagName(String tag_name);
}
