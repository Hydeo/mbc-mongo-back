package hello.repository.categories;

import hello.entity.Categories.LocalizedCategories;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoriesRepo extends MongoRepository<LocalizedCategories,String> {
    LocalizedCategories findByLang(String lang);
}
