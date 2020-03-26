package dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts4")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartEssentialRootDto {

    @XmlElement(name = "part4")
    private List<PartsEssentialsDto> parts;

    public PartEssentialRootDto() {
    }

    public PartEssentialRootDto(List<PartsEssentialsDto> dto) {
        this.parts = dto;
    }
}
