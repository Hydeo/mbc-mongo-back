package hello.entity.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hello.entity.tag.Tag;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "games")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "created_at"}, allowGetters = true)
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "nb_player_min")
    @NotNull
    protected int nbPlayerMin;

    @Column(name = "nbPlayerMax")
    @NotNull
    protected int nbPlayerMax;

    @Column(name = "timeToPlayMin")
    @NotNull
    protected int timeToPlayMin;

    @Column(name = "time_to_play_max")
    @NotNull
    protected int timeToPlayMax;

    @Column(name = "age_recommended")
    @NotNull
    protected int ageRecommended;

    @Column(name = "complexity")
    @NotNull
    protected double complexity;

    @Column(name = "type")
    @NotBlank
    protected String type;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name="links_game_tag",
            joinColumns = {@JoinColumn(name = "id_game")},
            inverseJoinColumns = {@JoinColumn(name = "id_tag")}
    )
    protected Set<Tag> tags = new HashSet() ;

    @Column(name="created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @OneToMany(mappedBy = "game")
    @JsonIgnoreProperties("game")
    public Set<GameLocalization> localizations = new HashSet<>();

    public Game() {
    }

    public Game(@NotNull int nbPlayerMin, @NotNull int nbPlayerMax, @NotNull int timeToPlayMin, @NotNull int timeToPlayMax, @NotNull int ageRecommended, @NotNull double complexity, @NotBlank String type) {
        this.nbPlayerMin = nbPlayerMin;
        this.nbPlayerMax = nbPlayerMax;
        this.timeToPlayMin = timeToPlayMin;
        this.timeToPlayMax = timeToPlayMax;
        this.ageRecommended = ageRecommended;
        this.complexity = complexity;
        this.type = type;
    }

    public Game addTag(Tag t) {
        this.tags.add(t);
        t.getGames().add(this);
        return this;
    }

    public Game removeTag(Tag t) {
        this.tags.remove(t);
        t.getGames().remove(this);
        return this;
    }
}
