package com.xmldemo.car_sale_xml.services;



import com.xmldemo.car_sale_xml.Entities.Car;
import dtos.CarByMakeDto;
import dtos.CarSeedDto;
import dtos.CarWithPartsDto;
//import dtos.CarWithPartsDto;

import java.math.BigDecimal;
import java.util.List;

public interface CarService {
    void seedCars(List<CarSeedDto> carSeedDtos);
    Car getRandomCar();
    List<CarByMakeDto> getCarsByMake(String make);
    List<CarWithPartsDto> getCarsWithParts();
    BigDecimal getPriceOfCar(long id);

}
