package json_product_shop.json1.repositories;

import json_product_shop.json1.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findAllById(long randomId);
    @Query("select c from Category as c order by c.products.size desc ")
    List<Category> getAll();
}
