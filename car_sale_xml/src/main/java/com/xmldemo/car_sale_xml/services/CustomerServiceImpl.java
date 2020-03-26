package com.xmldemo.car_sale_xml.services;


import com.xmldemo.car_sale_xml.Entities.Customer;
import com.xmldemo.car_sale_xml.repositories.CustomerRepository;
import dtos.CustomerSeedDto;
import dtos.CustomerWithBirthDateDto;
//import dtos.CustomerWithCarBoughtDto;
import dtos.CustomerWithCarBoughtDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.ValidationUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final CarService carService;
    private final ValidationUtil validationUtil;

    @Autowired
    public CustomerServiceImpl(ModelMapper modelMapper, CustomerRepository customerRepository, CarService carService, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
        this.carService = carService;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCustomer(List<CustomerSeedDto> customerSeedDtos) {
        if (this.customerRepository.count()!=0){
            return;
        }
        customerSeedDtos.stream().forEach(c->{
            Customer customer = this.modelMapper.map(c, Customer.class);
            System.out.println();
            //dunno why this was needed
            customer.setYoundDriver(c.isYoungDriver());
            this.customerRepository.saveAndFlush(customer);
        });
    }

    @Override
    public Customer getRandomCustomer() {
        Random random = new Random();
        long id = random.nextInt((int) this.customerRepository.count())+1;
        return this.customerRepository.findById(id);
    }
//
    @Override
    public List<CustomerWithBirthDateDto> getCustomersWithBirthdays() {
        List<Customer> customers = this.customerRepository.customersByBirthDay();
        System.out.println();
        List<CustomerWithBirthDateDto> dtos = new ArrayList<>();
        customers.stream().forEach(c->{
            CustomerWithBirthDateDto cbdDto = this.modelMapper.map(c, CustomerWithBirthDateDto.class);
            dtos.add(cbdDto);
        });
        return dtos;
    }
//
    @Override
    public Set<CustomerWithCarBoughtDto> getCustomersWithCarBought() {
        Set<Customer> customers = this.customerRepository.findAllByBoughtNotNullOderOrderByBought();
        Set<CustomerWithCarBoughtDto> result = new LinkedHashSet<>();
         customers.stream().forEach(c->{
            CustomerWithCarBoughtDto customerWithCarBoughtDto = this.modelMapper.map(c, CustomerWithCarBoughtDto.class);
            customerWithCarBoughtDto.setBoughtCount(c.getBought().size());

            //I know this is butt ugly but nothing else worked
            BigDecimal totalPrice = new BigDecimal(0);
            List<BigDecimal> prices = new ArrayList<>();
            c.getBought().stream().forEach(b->{
                long id = b.getCar().getId();
                System.out.println();
                BigDecimal price = this.carService.getPriceOfCar(b.getCar().getId());
                prices.add(price);
            });
            for (int i = 0; i < prices.size(); i++) {
                totalPrice = totalPrice.add(prices.get(i));
            }

            customerWithCarBoughtDto.setTotalPrice(totalPrice);
             System.out.println();
            result.add(customerWithCarBoughtDto);
        });
         return result;
    }
}
