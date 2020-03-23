package json_product_shop.json1.repositories;

import json_product_shop.json1.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBetweenAndBuyerIsNull(BigDecimal lower, BigDecimal higher);

}
