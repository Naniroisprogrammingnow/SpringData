package json_product_shop.json1.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CategoryProductsRevenueDto {
    @Expose
    private String name;
    @Expose
    private int productCount;
    @Expose
    private BigDecimal averagePrice;
    @Expose
    private BigDecimal totalRevenue;

    public CategoryProductsRevenueDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
