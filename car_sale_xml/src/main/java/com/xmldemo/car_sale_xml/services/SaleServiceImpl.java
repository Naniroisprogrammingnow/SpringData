package com.xmldemo.car_sale_xml.services;


import com.xmldemo.car_sale_xml.Entities.Car;
import com.xmldemo.car_sale_xml.Entities.Customer;
import com.xmldemo.car_sale_xml.Entities.Sale;
import com.xmldemo.car_sale_xml.repositories.SalesRepository;
import dtos.CarSoldDto;
import dtos.SaleDetailsDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {
    private final SalesRepository salesRepository;
    private final CarService carService;
    private final CustomerService customerService;
    private final ModelMapper modelMapper;


    @Autowired
    public SaleServiceImpl(SalesRepository salesRepository, CarService carService, CustomerService customerService, ModelMapper modelMapper) {
        this.salesRepository = salesRepository;
        this.carService = carService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedSales() {
        if (salesRepository.count()!=0){
            return;
        }
        double[] discounts = {0, 0.05, 0.1, 0.2, 0.3, 0.4, 0.5};
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            Sale sale = new Sale();
            int randomDiscount = random.nextInt(discounts.length);
            Car car = this.carService.getRandomCar();
            Customer buyer = this.customerService.getRandomCustomer();
            sale.setCar(car);
            sale.setBuyer(buyer);
            double discount = discounts[randomDiscount];
            if (buyer.isYoundDriver()){
                discount+=0.05;
            }
            sale.setDiscount(discount);
            this.salesRepository.saveAndFlush(sale);
        }
    }

    @Override
    public Set<SaleDetailsDto> getSalesDetails() {
        Set<Sale> sales = this.salesRepository.getAll();
        System.out.println();

        return sales.stream().map(s->{
            SaleDetailsDto saleDetailsDto = this.modelMapper.map(s, SaleDetailsDto.class);
            CarSoldDto carSoldDto = this.modelMapper.map(s.getCar(), CarSoldDto.class);
            saleDetailsDto.setCarSoldDto(carSoldDto);
            BigDecimal price = this.carService.getPriceOfCar(s.getCar().getId());
            saleDetailsDto.setPrice(price);
            saleDetailsDto.setPriceWithDiscount(price.multiply(new BigDecimal(1-saleDetailsDto.getDiscount())));
            System.out.println();
            return saleDetailsDto;
        }).collect(Collectors.toSet());
    }
}
