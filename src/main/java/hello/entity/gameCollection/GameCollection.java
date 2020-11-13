package hello.entity.gameCollection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hello.entity.game.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "game_collection")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "created_at"}, allowGetters = true)
@AllArgsConstructor
@NoArgsConstructor
public class GameCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "user_id")
    @NotNull
    public String userId;

    @Column(name = "is_public")
    @NotNull
    public Boolean isPublic;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "links_game_game_collection",
            joinColumns = {@JoinColumn(name = "id_game_collection")},
            inverseJoinColumns = {@JoinColumn(name = "id_game")}
    )
    public Set<Game> games = new HashSet<>();

    //public ArrayList<GameMask> gameMask;


    public GameCollection(@NotNull String userId, @NotNull Boolean isPublic) {
        this.userId = userId;
        this.isPublic = isPublic;
    }
}
