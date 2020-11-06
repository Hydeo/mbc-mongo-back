package hello.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"created_at"},
        allowGetters = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    @Column
    @NotBlank
    public String identifier;

    public User() {
    }

    public User(String UID, String identifier) {
        this.id = UID;
        this.identifier = identifier;
    }


    @Override
    public String toString() {
        return String.format("User[uid = %s,identifier=%s", id, identifier);
    }

    public String getUid() {
        return id;
    }

    public void setUid(String uid) {
        this.id = uid;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
