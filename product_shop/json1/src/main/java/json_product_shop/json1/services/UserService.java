package json_product_shop.json1.services;

import json_product_shop.json1.dtos.UserSeedDto;
import json_product_shop.json1.dtos.UserSellerDto;
import json_product_shop.json1.entities.User;

import java.util.List;

public interface UserService {
    void seedUsers(UserSeedDto[] userSeedDtos);
    User getRandomUser();
    List<UserSellerDto> getSellers();
}
