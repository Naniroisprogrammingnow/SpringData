package json_product_shop.json1.services.impl;

import json_product_shop.json1.dtos.CategoryProductsRevenueDto;
import json_product_shop.json1.dtos.CategorySeedDto;
import json_product_shop.json1.entities.Category;
import json_product_shop.json1.entities.Product;
import json_product_shop.json1.repositories.CategoryRepository;
import json_product_shop.json1.services.CategoryService;
import json_product_shop.json1.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategories(CategorySeedDto[] categorySeedDtos) {
        if (this.categoryRepository.count()!=0){
            return;
        }

        Arrays.stream(categorySeedDtos)
                .forEach(categorySeedDto -> {
                    if (this.validationUtil.isValid(categorySeedDto)){
                        Category category = modelMapper.map(categorySeedDto, Category.class);
                        this.categoryRepository.saveAndFlush(category);
                    }else {
                        this.validationUtil.violations(categorySeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });
    }

    @Override
    public Set<Category> getRandomCategories() {
        Random random = new Random();
        Set<Category> categories = new HashSet<>();
        int randomCounter = random.nextInt(3)+1;

        for (int i = 0; i < randomCounter; i++) {
            long randomId = random.nextInt((int) this.categoryRepository.count())+1;
            categories.add(this.categoryRepository.findAllById(randomId));
        }
        
        return categories;
    }

    @Override
    public Set<CategoryProductsRevenueDto> getAllCategoriesByProductRevenue() {
        List<Category> categoryList = this.categoryRepository.getAll();
        Set<CategoryProductsRevenueDto> setCPRD = new LinkedHashSet<>();
         categoryList.stream().map(c->{
            CategoryProductsRevenueDto cprd = this.modelMapper.map(c, CategoryProductsRevenueDto.class);
            cprd.setProductCount(c.getProducts().size());
            BigDecimal total = c.getProducts().stream().map(Product::getPrice).reduce((a,b)->a.add(b)).get();
            cprd.setTotalRevenue(total);
            BigDecimal divisor = BigDecimal.valueOf(c.getProducts().size());
            BigDecimal average = total.divide(divisor,2, RoundingMode.HALF_UP);
            cprd.setAveragePrice(average);
            return cprd;
        }).forEach(e-> setCPRD.add(e));
         return setCPRD;
    }
}
