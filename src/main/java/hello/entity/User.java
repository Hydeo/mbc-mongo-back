package hello.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hello.entity.gameCollection.GameCollection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

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

    @Column(name = "created_at",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;


    public User(@NotBlank String firebaseUID, @NotBlank String firebaseIdentifier) {
        this.firebaseUID = firebaseUID;
        this.firebaseIdentifier = firebaseIdentifier;
    }
}
