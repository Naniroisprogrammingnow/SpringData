package json_product_shop.json1.services;

import json_product_shop.json1.dtos.ProductInRangeDto;
import json_product_shop.json1.dtos.ProductSeedDto;

import java.util.List;

public interface ProductService {
    void seedProducts(ProductSeedDto[] productSeedDtos);
    List<ProductInRangeDto> getAllInRange();
}
