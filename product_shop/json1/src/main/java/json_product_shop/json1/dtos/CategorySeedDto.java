package json_product_shop.json1.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

public class CategorySeedDto {

    @Expose
    @Length(min = 3, max = 15, message = "Invalid length!")
    private String name;

    private CategorySeedDto(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
