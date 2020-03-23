package json_product_shop.json1.services.impl;

import json_product_shop.json1.dtos.ProductSoldDto;
import json_product_shop.json1.dtos.UserSeedDto;
import json_product_shop.json1.dtos.UserSellerDto;
import json_product_shop.json1.entities.Product;
import json_product_shop.json1.entities.User;
import json_product_shop.json1.repositories.UserRepository;
import json_product_shop.json1.services.UserService;
import json_product_shop.json1.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedUsers(UserSeedDto[] userSeedDtos) {
        if (this.userRepository.count()!=0){
            return;
        }
        Arrays.stream(userSeedDtos).forEach(userSeedDto -> {
            if (this.validationUtil.isValid(userSeedDto)){
                User user = this.modelMapper.map(userSeedDto, User.class);
                System.out.println();
                Random random = new Random();
                Set<User> friends = new HashSet<>();

//                int friendCount = random.nextInt(3);
//                for (int i = 0; i < friendCount; i++) {
//                    friends.add(this.getRandomUser());
//                }
//                user.setFriends(friends);
                this.userRepository.saveAndFlush(user);
            }else {
                this.validationUtil.violations(userSeedDto)
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }
        });
    }

    @Override
    public User getRandomUser() {
        Random random = new Random();
        long randomId = random.nextInt((int) this.userRepository.count())+1;
        User user =this.userRepository.findAllById(randomId);
        return user;
    }

    @Override
    public List<UserSellerDto> getSellers() {
        List<User> sellers = this.userRepository.findAllBySold();
        List<UserSellerDto> usd = new LinkedList<>();
        sellers.stream().map(s->{
            UserSellerDto userSellerDto = this.modelMapper.map(s, UserSellerDto.class);
            List<ProductSoldDto> productSoldDtos = new LinkedList<>();
            Set<Product> products = s.getSold();
            for (Product product : products) {
                if (product.getBuyer()==null){
                    continue;
                }
                ProductSoldDto productSoldDto = modelMapper.map(product, ProductSoldDto.class);
                productSoldDtos.add(productSoldDto);
            }
            userSellerDto.setProductSoldDtos(productSoldDtos);
            return userSellerDto;
        }).forEach(e->usd.add(e));
        return usd;
    }
}
