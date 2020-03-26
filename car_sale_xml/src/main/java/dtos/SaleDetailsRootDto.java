package dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;
@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleDetailsRootDto {
    @XmlElement(name = "sale")
    private Set<SaleDetailsDto> sales;

    public SaleDetailsRootDto() {
    }

    public SaleDetailsRootDto(Set<SaleDetailsDto> sales) {
        this.sales = sales;
    }

    public Set<SaleDetailsDto> getSales() {
        return sales;
    }

    public void setSales(Set<SaleDetailsDto> sales) {
        this.sales = sales;
    }
}
