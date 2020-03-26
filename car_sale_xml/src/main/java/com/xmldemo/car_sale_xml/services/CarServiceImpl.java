package com.xmldemo.car_sale_xml.services;


import com.xmldemo.car_sale_xml.Entities.Car;
import com.xmldemo.car_sale_xml.Entities.Part;
import com.xmldemo.car_sale_xml.repositories.CarRepository;
import dtos.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.ValidationUtil;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final PartsService partsService;
    private Random random = new Random();
    private final ValidationUtil validationUtil;


    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, PartsService partsService, ValidationUtil validationUtil) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.partsService = partsService;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCars(List<CarSeedDto> carSeedDtos) {
        if (this.carRepository.count()!=0){
            return;
        }

        carSeedDtos.stream().forEach(c->{
            if (this.validationUtil.isValid(c)){
                Car car = this.modelMapper.map(c, Car.class);
                int countParts = random.nextInt(21-10)+10;
                Set<Part> parts = new LinkedHashSet<>();
                for (int i = 0; i < countParts; i++) {
                    Part part = this.partsService.getRandomPart();
                    parts.add(part);
                }
                car.setParts(parts);
                this.carRepository.saveAndFlush(car);
            }else {
                this.validationUtil.violations(c)
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }

        });
    }

    @Override
    public Car getRandomCar() {
        long id = this.random.nextInt((int) this.carRepository.count())+1;
        return this.carRepository.findById(id);
    }
//
    @Override
    public List<CarByMakeDto> getCarsByMake(String make) {
        List<Car> carsByMake = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make);

        return carsByMake.stream().map(c->{
            return this.modelMapper.map(c, CarByMakeDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public List<CarWithPartsDto> getCarsWithParts() {
        List<Car> cars = this.carRepository.getAll();

        return cars.stream().map(c->{
            CarWithPartsDto carWithPartsDto = this.modelMapper.map(c, CarWithPartsDto.class);
            List<PartsEssentialsDto> dto = c.getParts().stream().map(p->{
                PartsEssentialsDto pDto = this.modelMapper.map(p, PartsEssentialsDto.class);
                return pDto;
            }).collect(Collectors.toList());
            PartEssentialRootDto pRoot = new PartEssentialRootDto(dto);
            carWithPartsDto.setParts(pRoot);
            return carWithPartsDto;
        }).collect(Collectors.toList());
    }

    @Override
    public BigDecimal getPriceOfCar(long id) {
        Car car = this.carRepository.findById(id);
        BigDecimal price = new BigDecimal(0);
        Set<Part> parts = car.getParts();
        for (Part part : parts) {
            price = price.add(part.getPrice());
        }
        System.out.println();
        return price;
    }
}
