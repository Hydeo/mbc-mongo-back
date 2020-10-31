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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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
    public void testTransactional() {
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


        Set<Tag> sTags = new HashSet<>();
        sTags.add(t1);


    }

    public void testCreation(){
        Game g = createGame();
        Tag t = createTag();

        gr.save(g.addTag(t));

        deleteGame(g);
        deleteTag(t);

    }

    public Game createGame() {
        Game g1 = new Game(1, 2, 3, 4, 5, 10.0, "type");
        return gr.save(g1);
    }

    public Tag createTag() {
        Tag t1 = new Tag();
        t1.name = "t1";
        createTagTranslation().gameTag = t1;
        return tr.save(t1);
    }

    public TagTrad createTagTranslation() {
        TagTrad tt1 = new TagTrad();
        tt1.setLang("eng");
        tt1.setTrad("trad1");
        return tt1;
    }

    public Game addTagToGame(Game g, Tag t) {
        g.addTag(t);
        return gr.save(g);
    }

    public Game removeTagFromGame(Game g, Tag t) {
        g.removeTag(t);
        return gr.save(g);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteGame(Game g) {
        gr.delete(g);
    }


    @Transactional(rollbackFor = {Exception.class})
    public void deleteTag(Tag t) {
        tr.delete(t);
    }
}
