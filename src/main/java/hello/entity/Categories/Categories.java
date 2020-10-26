package hello.entity.Categories;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "Catagories")
public class Categories {
    @Id
    public Long id;
    @Column(name = "name")
    public String name;
}