package hello.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hello.entity.gameCollection.GameCollection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity

@Getter
@Setter
@NoArgsConstructor

@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"created_at"},
        allowGetters = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="firebase_UID")
    @NotBlank
    private String firebaseUID;

    @Column(name = "firebase_identifier")
    @NotBlank
    private String firebaseIdentifier;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private GameCollection gameCollection;

    public User(@NotBlank String firebaseUID, @NotBlank String firebaseIdentifier) {
        this.firebaseUID = firebaseUID;
        this.firebaseIdentifier = firebaseIdentifier;
    }
}
