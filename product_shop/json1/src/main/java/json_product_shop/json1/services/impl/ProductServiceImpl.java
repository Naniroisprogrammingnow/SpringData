package json_product_shop.json1.services.impl;

import json_product_shop.json1.dtos.ProductInRangeDto;
import json_product_shop.json1.dtos.ProductSeedDto;
import json_product_shop.json1.entities.Category;
import json_product_shop.json1.entities.Product;
import json_product_shop.json1.entities.User;
import json_product_shop.json1.repositories.ProductRepository;
import json_product_shop.json1.services.CategoryService;
import json_product_shop.json1.services.ProductService;
import json_product_shop.json1.services.UserService;
import json_product_shop.json1.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ModelMapper mapper;
    private final ProductRepository productRepository;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ModelMapper mapper, ProductRepository productRepository, ValidationUtil validationUtil, UserService userService, CategoryService categoryService) {
        this.mapper = mapper;
        this.productRepository = productRepository;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedProducts(ProductSeedDto[] productSeedDtos) {
        if (this.productRepository.count()!=0){
            System.out.println("Products already exist");
            return;
        }

        Arrays.stream(productSeedDtos).forEach(productSeedDto -> {
            if (this.validationUtil.isValid(productSeedDto)){
                Product product = this.mapper.map(productSeedDto, Product.class);
                product.setSeller(this.userService.getRandomUser());
                product.setCategories(this.categoryService.getRandomCategories());
                Random random = new Random();
                int buyer = random.nextInt(2);
                if (buyer==1){
                    product.setBuyer(this.userService.getRandomUser());
                }
                System.out.println();
                this.productRepository.saveAndFlush(product);
            }else {
                this.validationUtil.violations(productSeedDto)
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }
        });
    }

    @Override
    public List<ProductInRangeDto> getAllInRange() {
        return this.productRepository.findAllByPriceBetweenAndBuyerIsNull(BigDecimal.valueOf(500), BigDecimal.valueOf(1000))
                .stream()
                .map(p->{
                    ProductInRangeDto productInRangeDto = this.mapper.map(p, ProductInRangeDto.class);
                    productInRangeDto.setSeller(String.format("%s %s",
                            p.getSeller().getFirstName(),
                            p.getSeller().getLastName()));
                    return productInRangeDto;
                }).collect(Collectors.toList());
    }
}
