package com.xmldemo.car_sale_xml.services;



import com.xmldemo.car_sale_xml.Entities.Customer;
import dtos.CustomerSeedDto;
import dtos.CustomerWithBirthDateDto;
import dtos.CustomerWithCarBoughtDto;
//import dtos.CustomerWithCarBoughtDto;

import java.util.List;
import java.util.Set;

public interface CustomerService {
   void seedCustomer(List<CustomerSeedDto> customerSeedDtos);
    Customer getRandomCustomer();
    List<CustomerWithBirthDateDto> getCustomersWithBirthdays();
    Set<CustomerWithCarBoughtDto> getCustomersWithCarBought();
}
