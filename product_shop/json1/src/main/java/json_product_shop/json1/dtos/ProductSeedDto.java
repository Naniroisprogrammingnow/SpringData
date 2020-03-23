package json_product_shop.json1.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class ProductSeedDto {
    @Expose
    private String name;
    @Expose
    @Min(value = 0, message = "Price should be positive")
    private BigDecimal price;

    public ProductSeedDto (){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
