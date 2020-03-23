package json_product_shop.json1.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UserSellerDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private List<ProductSoldDto> productSoldDtos;

    public UserSellerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ProductSoldDto> getProductSoldDtos() {
        return productSoldDtos;
    }

    public void setProductSoldDtos(List<ProductSoldDto> productSoldDtos) {
        this.productSoldDtos = productSoldDtos;
    }
}
