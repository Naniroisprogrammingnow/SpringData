package com.xmldemo.car_sale_xml.Entities;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity{
    private double discount;
    private Car car;
    private Customer buyer;

    public Sale(){}

    @OneToOne
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Customer getBuyer() {
        return buyer;
    }

    public void setBuyer(Customer buyer) {
        this.buyer = buyer;
    }

    @Column(name = "discount")
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
