package hello.entity.tag;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "game_tag_localizations")
@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@JsonIgnoreProperties(value = {"created_at"},
        allowGetters = true)
public class TagLocalization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "lang")
    @NotBlank
    public String lang;

    @Column(name = "trad")
    @NotBlank
    private String trad;

    @ManyToOne
    @JoinColumn(name="id_game_tag", nullable=false)
    @NotNull
    public Tag gameTag;

    @Column(name = "created_at",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    public TagLocalization(@NotBlank String lang, @NotBlank String trad, @NotNull Tag gameTag) {
        this.lang = lang;
        this.trad = trad;
        this.gameTag = gameTag;
    }
}
