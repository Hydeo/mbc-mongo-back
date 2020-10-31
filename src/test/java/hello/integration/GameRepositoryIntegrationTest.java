package hello.integration;


import hello.app;
import hello.entity.game.Game;
import hello.entity.gameCollection.GameCollection;
import hello.repository.game.GameRepo;
import hello.repository.gameCollection.GameCollectionRepo;
import hello.repository.user.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes= app.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class GameRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GameRepo gameRepo;

    @MockBean
    private GameCollectionRepo gameCollectionRepo;

    @MockBean
    private UserRepo userRepo;

    @Test
    public void saveNewGame(){
        // given
        Game g = new Game(1, 2, 3, 4, 5, 10.0, "type");
        entityManager.persist(g);

        //then
        assertThat(g.getId())
                .isNotNull();
    }

    @Test
    public void deleteGame(){
        // given
        Game g1 = new Game(1, 2, 3, 4, 5, 10.0, "type");
        entityManager.persist(g1);
        entityManager.remove(g1);
        Optional<Game> g2 = gameRepo.findById(g1.getId());

        assertThat(g1.getId())
                .isNotNull();
        assertThat(g2.isEmpty())
                .isTrue();
    }

    @Test
    public void whenFindById_thenReturnGame() {
        // given
        Game g = new Game(1, 2, 3, 4, 5, 10.0, "type");
        entityManager.persist(g);

        // when
        Optional<Game> gameFound = gameRepo.findById(g.getId());

        // then
        assertThat(gameFound.get()).isEqualTo(g);
    }
}
