package dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars4")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsWithPartsRootDto {

    @XmlElement(name = "car4")
    private List<CarWithPartsDto> cars;

    public CarsWithPartsRootDto() {
    }

    public CarsWithPartsRootDto(List<CarWithPartsDto> cars) {
        this.cars = cars;
    }
}
