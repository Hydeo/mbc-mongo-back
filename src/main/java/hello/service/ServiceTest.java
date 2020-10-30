package hello.service;

import hello.entity.tag.Tag;
import hello.entity.tag.TagTrad;
import hello.repository.tag.TagRepo;
import hello.repository.tag.TagTradRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
public class ServiceTest {

    @Autowired
    TagRepo tr;

    @Autowired
    TagTradRepo ttr;

    @Transactional(rollbackFor = {Exception.class})
    public void testTransactional(){
        Tag t1 = new Tag();
        t1.name = "t1";

        TagTrad tt1 = new TagTrad();
        TagTrad tt2 = new TagTrad();

        tt1.gameTag = t1;
        tt1.setLang("tt1");
        tt1.setTrad("trad1");

        tt2.gameTag = t1;
        tt2.setLang("tt2");
        tt2.setTrad("trad2");

        tr.save(t1);

        ttr.save(tt1);
        ttr.save(tt2);


    }
}
