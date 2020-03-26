package com.xmldemo.car_sale_xml.Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity{
    private String name;
    private LocalDateTime birthDate;
    private boolean isYoungDriver;
    private Set<Sale> bought;

    public Customer(){}


    @Column(name = "birth_date")
    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "is_young_driver")
    public boolean isYoundDriver() {
        return isYoungDriver;
    }

    public void setYoundDriver(boolean youndDriver) {
        isYoungDriver = youndDriver;
    }

    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    public Set<Sale> getBought() {
        return bought;
    }

    public void setBought(Set<Sale> bought) {
        this.bought = bought;
    }
}
