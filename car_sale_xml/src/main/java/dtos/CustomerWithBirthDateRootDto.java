package dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerWithBirthDateRootDto {
    @XmlElement(name = "customer")
    List<CustomerWithBirthDateDto> customers;

    public CustomerWithBirthDateRootDto() {
    }

    public CustomerWithBirthDateRootDto(List<CustomerWithBirthDateDto> customers) {
        this.customers = customers;
    }

    public List<CustomerWithBirthDateDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerWithBirthDateDto> customers) {
        this.customers = customers;
    }
}
