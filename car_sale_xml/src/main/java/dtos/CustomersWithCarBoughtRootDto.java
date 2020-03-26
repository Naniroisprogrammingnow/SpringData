package dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "customers5")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersWithCarBoughtRootDto {


    public CustomersWithCarBoughtRootDto(Set<CustomerWithCarBoughtDto> customers) {
        this.customers = customers;
    }

    public CustomersWithCarBoughtRootDto() {
    }

    @XmlElement(name = "customer5")
    private Set<CustomerWithCarBoughtDto> customers;

}
