package hello.repository.categories;

import hello.entity.Categories.Categories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepo extends CrudRepository<Categories,Long> {

}
