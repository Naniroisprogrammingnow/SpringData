package dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarByMakeRootDto {
    @XmlElement(name = "car")
    private List<CarByMakeDto> carByMakeDtoList;

    public CarByMakeRootDto() {
    }

    public CarByMakeRootDto(List<CarByMakeDto> carByMakeDtoList) {
        this.carByMakeDtoList = carByMakeDtoList;
    }

    public List<CarByMakeDto> getCarByMakeDtoList() {
        return carByMakeDtoList;
    }

    public void setCarByMakeDtoList(List<CarByMakeDto> carByMakeDtoList) {
        this.carByMakeDtoList = carByMakeDtoList;
    }
}
