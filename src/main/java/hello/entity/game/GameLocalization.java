package hello.entity.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "game_localizations")
@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@NoArgsConstructor

@JsonIgnoreProperties(value = {"created_at"},
        allowGetters = true)
public class GameLocalization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String title;

    @Column
    public String description;

    @Column
    public String imageUrl;

    @Column
    public String lang;

    @ManyToOne
    @JoinColumn(name="game_id",nullable = false)
    private Game game;

    @Column(name = "created_at",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;


    public GameLocalization(Game game, String title, String description, String imageUrl, String lang) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.lang = lang;
        this.game = game;
    }

}
