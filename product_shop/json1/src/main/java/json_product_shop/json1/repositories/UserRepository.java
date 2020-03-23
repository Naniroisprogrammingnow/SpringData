package json_product_shop.json1.repositories;

import json_product_shop.json1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findAllById(long randomId);
    @Query("select u from User as u where u.sold.size>=1 order by u.lastName, u.firstName")
    List<User> findAllBySold();
}
