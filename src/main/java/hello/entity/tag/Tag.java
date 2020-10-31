package hello.entity.tag;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hello.entity.game.Game;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "game_tags")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"created_at"},
        allowGetters = true)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name")
    @NotBlank
    public String name;

    @OneToMany(mappedBy="gameTag")
    public Set<TagTrad> localization = new HashSet();

    @Column(name = "created_at",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnoreProperties("tags")
    private Set<Game> games = new HashSet();
}
