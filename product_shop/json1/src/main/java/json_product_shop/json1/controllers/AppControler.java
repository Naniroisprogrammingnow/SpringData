package json_product_shop.json1.controllers;

import com.google.gson.Gson;
import json_product_shop.json1.dtos.*;
import json_product_shop.json1.entities.User;
import json_product_shop.json1.services.CategoryService;
import json_product_shop.json1.services.ProductService;
import json_product_shop.json1.services.UserService;
import json_product_shop.json1.utils.FileUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Controller
public class AppControler implements CommandLineRunner {

    private final Gson gson;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final FileUtil fileUtil;
    private Scanner scanner = new Scanner(System.in);

    public AppControler(Gson gson, CategoryService categoryService, UserService userService, ProductService productService, FileUtil fileUtil) {
        this.gson = gson;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.fileUtil = fileUtil;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedCategories();
        this.seedUsers();
        this.seedProduct();


        while(true){
            System.out.println("Enter task numer(or 0 to exit): ");
            int num = Integer.parseInt(scanner.nextLine());

            if (num==0){
                break;
            }

            switch (num){
                case 1:
                    this.findProductInRange();
                    break;
                case 2:
                    this.findSuccessfulSellers();
                    break;
                case 3:
                    this.getCategoriesByProductRevenue();
                    break;
            }

        }

    }

    private void findSuccessfulSellers() throws IOException {
        List<UserSellerDto> sellers = this.userService.getSellers();
        String json = this.gson.toJson(sellers);
        this.fileUtil.write(json, "src/main/resources/files/ex2");
    }

    private void getCategoriesByProductRevenue() throws IOException {
        Set<CategoryProductsRevenueDto> allCategoriesByProductRevenue = this.categoryService.getAllCategoriesByProductRevenue();
        String json = this.gson.toJson(allCategoriesByProductRevenue);
        this.fileUtil.write(json, "src/main/resources/files/ex3");
        System.out.println();
    }

    private void findProductInRange() throws IOException {
        List<ProductInRangeDto> allInRange = this.productService.getAllInRange();
        String json = this.gson.toJson(allInRange);
        this.fileUtil.write(json, "src/main/resources/files/ex1" );
        System.out.println();
    }

    private void seedProduct() throws FileNotFoundException {
        ProductSeedDto[] productSeedDtos =
                this.gson.fromJson(new FileReader("src/main/resources/files/products.json"),
                        ProductSeedDto[].class);
        this.productService.seedProducts(productSeedDtos);
    }

    private void seedUsers() throws FileNotFoundException {
        UserSeedDto[] userSeedDtos =
                this.gson.fromJson(new FileReader("src/main/resources/files/users.json"),
                        UserSeedDto[].class);

        this.userService.seedUsers(userSeedDtos);
    }

    private void seedCategories() throws FileNotFoundException {
        CategorySeedDto[] categorySeedDtos =
                this.gson.fromJson(new FileReader("src/main/resources/files/categories.json"),
                        CategorySeedDto[].class);
        this.categoryService.seedCategories(categorySeedDtos);
    }
}
