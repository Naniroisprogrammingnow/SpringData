package com.xmldemo.car_sale_xml.services;


import dtos.SaleDetailsDto;

import java.util.Set;

public interface SaleService {
    void seedSales();
    Set<SaleDetailsDto> getSalesDetails();
}
