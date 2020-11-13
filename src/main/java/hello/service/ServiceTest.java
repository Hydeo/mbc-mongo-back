package hello.service;

import hello.entity.User;
import hello.entity.game.Game;
import hello.entity.game.GameLocalization;
import hello.entity.gameCollection.GameCollection;
import hello.entity.tag.Tag;
import hello.entity.tag.TagLocalization;
import hello.repository.game.GameLocalizationRepo;
import hello.repository.game.GameRepo;
import hello.repository.gameCollection.GameCollectionRepo;
import hello.repository.tag.TagRepo;
import hello.repository.tag.TagLocalizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Service
public class ServiceTest {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    TagRepo tr;

    @Autowired
    TagLocalizationRepo tlr;

    @Autowired
    GameRepo gr;

    @Autowired
    GameLocalizationRepo glr;

    @Autowired
    GameCollectionRepo gcr;

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

        tlr.save(tt1);
        tlr.save(tt2);


        Set<Tag> sTags = new HashSet<>();
        sTags.add(t1);
    }

    @Transactional
    public void testCreation() {

        User u = new User(
                "7whXumP1nOTVPxEJrDxpOKvNTo02",
                "identifier"
        );
        Game g = new Game(
                1,
                2,
                3,
                4,
                5,
                10.0,
                "type"
        );
        g.addGameLocalization(
                new GameLocalization(
                        g,
                        "title",
                        "description",
                        "https://cf.geekdo-images.com/_HhIdavYW-hid20Iq3hhmg__imagepage/img/JUEcmeR5Cm5haFjoG5f_Uv8Zlws=/fit-in/900x600/filters:no_upscale():strip_icc()/pic5055631.jpg",
                        "eng"
                )
        );

        g.addTag(
                new Tag(
                        "TagName"
                )
        );

        GameCollection gc = new GameCollection(
                u,
                true
        );

        gc.getGames().add(g);

        entityManager.persist(u);

        entityManager.persist(g);

        entityManager.persist(gc);


        //GameCollection gc = createGameCollection();
        //addGameToGameCollection(gc,g);

        //deleteGame(g);
        //deleteTag(t);

    }

    /*public GameCollection createGameCollection(){
        GameCollection gc = new GameCollection(1L,true);
        return gcr.save(gc);
    }*/
    public GameCollection addGameToGameCollection(GameCollection gc, Game g) {
        gc.getGames().add(g);
        return gcr.save(gc);
    }

    public Game createGame() {
        Game g1 = new Game(1, 2, 3, 4, 5, 10.0, "type");
        return gr.save(g1);
    }

    public Tag createTag() {
        Tag t1 = new Tag();
        t1.name = "t1";
        tr.save(t1);
        createTagTranslation(t1);
        return t1;
    }

    public TagLocalization createTagTranslation(Tag t) {
        TagLocalization tt1 = new TagLocalization();
        tt1.setLang("eng");
        tt1.setTrad("trad1");
        tt1.gameTag = t;
        return tlr.save(tt1);
    }

    public GameLocalization createGameLocalization(Game game) {
        GameLocalization gl = new GameLocalization(game, "GameTitle", "GameDescription", "https://cf.geekdo-images.com/cpYagRqtBCFedYbqB3WcKg__imagepage/img/GoJTHeMQ0dhniBJa3tFo7-3xBpM=/fit-in/900x600/filters:no_upscale():strip_icc()/pic5598658.jpg", "eng");
        Set<GameLocalization> gameLocalizationSet = game.getLocalizations();
        gameLocalizationSet.add(gl);
        game.setLocalizations(gameLocalizationSet);
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
        glr.deleteInBatch(g.getLocalizations());

        gr.delete(g);
    }


    @Transactional(rollbackFor = {Exception.class})
    public void deleteTag(Tag t) {
        tr.delete(t);
    }
}
