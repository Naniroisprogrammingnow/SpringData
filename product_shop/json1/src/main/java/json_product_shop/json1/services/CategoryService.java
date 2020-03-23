package json_product_shop.json1.services;

import json_product_shop.json1.dtos.CategoryProductsRevenueDto;
import json_product_shop.json1.dtos.CategorySeedDto;
import json_product_shop.json1.entities.Category;

import java.util.Set;

public interface CategoryService {
    void seedCategories(CategorySeedDto[] categorySeedDtos);
    Set<Category> getRandomCategories();
    Set<CategoryProductsRevenueDto> getAllCategoriesByProductRevenue();
}
