package dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierNotImporterRootDto {

    @XmlElement(name = "supplier")
    private List<SupplierNotImporterDto> supplierNotImporterDtos;

    public SupplierNotImporterRootDto(List<SupplierNotImporterDto> supplierNotImporters) {
        this.supplierNotImporterDtos = supplierNotImporters;
    }

    public SupplierNotImporterRootDto() {
    }

    public List<SupplierNotImporterDto> getSupplierNotImporterDtos() {
        return supplierNotImporterDtos;
    }

    public void setSupplierNotImporterDtos(List<SupplierNotImporterDto> supplierNotImporterDtos) {
        this.supplierNotImporterDtos = supplierNotImporterDtos;
    }
}
