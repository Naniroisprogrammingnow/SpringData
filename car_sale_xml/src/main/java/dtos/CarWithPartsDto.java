package dtos;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.util.Set;
@XmlRootElement(name = "car4")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarWithPartsDto {
    @XmlAttribute
    private String make;
    @XmlAttribute
    private String model;
    @XmlAttribute(name = "travelled-distance")
    private long travelledDistance;
    @XmlElement(name = "parts4")
    private PartEssentialRootDto parts;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public PartEssentialRootDto getParts() {
        return parts;
    }

    public void setParts(PartEssentialRootDto parts) {
        this.parts = parts;
    }

    public CarWithPartsDto() {
    }
}
