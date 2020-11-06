package hello.service;

import hello.entity.game.Game;
import hello.entity.game.GameLocalization;
import hello.entity.tag.Tag;
import hello.entity.tag.TagLocalization;
import hello.repository.game.GameLocalizationRepo;
import hello.repository.game.GameRepo;
import hello.repository.tag.TagRepo;
import hello.repository.tag.TagLocalizationRepo;
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
    TagLocalizationRepo ttr;

    @Autowired
    GameRepo gr;

    @Autowired
    GameLocalizationRepo glr;

    @Transactional(rollbackFor = {Exception.class})
    public void testTransactional() {
        Tag t1 = new Tag();
        t1.name = "t1";

        TagLocalization tt1 = new TagLocalization();
        TagLocalization tt2 = new TagLocalization();

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
        GameLocalization gl = createGameLocalization(g);

        g.addTag(t);
        
        g = gr.save(g);

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

    public TagLocalization createTagTranslation() {
        TagLocalization tt1 = new TagLocalization();
        tt1.setLang("eng");
        tt1.setTrad("trad1");
        return tt1;
    }

    public GameLocalization createGameLocalization(Game game){
        GameLocalization gl = new GameLocalization(game,"GameTitle", "GameDescription","https://cf.geekdo-images.com/cpYagRqtBCFedYbqB3WcKg__imagepage/img/GoJTHeMQ0dhniBJa3tFo7-3xBpM=/fit-in/900x600/filters:no_upscale():strip_icc()/pic5598658.jpg","eng");
        Set<GameLocalization> gameLocalizationSet = game.getLocalization();
        gameLocalizationSet.add(gl);
        game.setLocalization(gameLocalizationSet);
        return glr.save(gl);
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

        //Delete all localizations linked to the game before deleting the game
        glr.deleteInBatch(g.getLocalization());

        gr.delete(g);
    }


    @Transactional(rollbackFor = {Exception.class})
    public void deleteTag(Tag t) {
        tr.delete(t);
    }
}
