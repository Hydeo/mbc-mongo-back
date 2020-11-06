package hello.repository.tag;

import hello.entity.tag.TagLocalization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagLocalizationRepo extends JpaRepository<TagLocalization,String>,TagRepoCustom {
    //Tag findByTagName(String tag_name);
}
