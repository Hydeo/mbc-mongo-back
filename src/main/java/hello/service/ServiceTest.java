package hello.service;

import hello.entity.game.Game;
import hello.entity.tag.Tag;
import hello.entity.tag.TagTrad;
import hello.repository.game.GameRepo;
import hello.repository.tag.TagRepo;
import hello.repository.tag.TagTradRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Component
public class ServiceTest {

    @Autowired
    TagRepo tr;

    @Autowired
    TagTradRepo ttr;


    @Autowired
    GameRepo gr;

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

        Game g1 = new Game(1,2,3,4,5,10.0,"type");

        Set<Tag> sTags = new HashSet<>();
        sTags.add(t1);

        g1.addTag(t1);

        gr.save(g1);
    }


    @Transactional(rollbackFor = {Exception.class})
    public void deleteGame(){
       // Optional<Game> g = gr.findById(1L);
        Optional<Tag> t = tr.findById(51L);
        /*if(g.isPresent() && t.isPresent()) {
           Game gg = g.get();
           Tag tt = t.get();

           gg.removeTag(tt);

           gr.save(gg);
        }*/

    }


    @Transactional(rollbackFor = {Exception.class})
    public void deleteTag(){
        Optional<Tag> t = tr.findById(51L);

        if(t.isPresent()) {
            tr.delete(t.get());
        }

    }
}
