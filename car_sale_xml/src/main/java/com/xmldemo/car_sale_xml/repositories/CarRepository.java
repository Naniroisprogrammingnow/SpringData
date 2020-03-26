package com.xmldemo.car_sale_xml.repositories;


import com.xmldemo.car_sale_xml.Entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findById(long id);
    List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);
    @Query("select c from Car as c")
    List<Car> getAll();
}
