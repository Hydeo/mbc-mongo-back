package hello.entity.gameCollection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hello.entity.User;
import hello.entity.game.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "game_collection")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"created_at"}, allowGetters = true)
@AllArgsConstructor
@NoArgsConstructor
public class GameCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    public User user;

    @Column(name = "is_public")
    @NotNull
    public Boolean isPublic;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "links_game_game_collection",
            joinColumns = {@JoinColumn(name = "id_game_collection")},
            inverseJoinColumns = {@JoinColumn(name = "id_game")}
    )
    public Set<Game> games = new HashSet<>();

    //public ArrayList<GameMask> gameMask;

    @Column(name="created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
    
    public GameCollection(@NotNull User user, @NotNull Boolean isPublic) {
        this.user = user;
        this.isPublic = isPublic;
    }
}
