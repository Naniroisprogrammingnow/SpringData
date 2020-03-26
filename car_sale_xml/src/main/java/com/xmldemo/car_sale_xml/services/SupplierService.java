package com.xmldemo.car_sale_xml.services;



import com.xmldemo.car_sale_xml.Entities.Supplier;
import dtos.SupplierNotImporterDto;
import dtos.SupplierSeedDto;

import java.util.List;

public interface SupplierService {
    void seedSuppliers(List<SupplierSeedDto> supplierSeedDtos);
    Supplier getRandomSupplier();
    Supplier getSupplierId(long id);
    List<SupplierNotImporterDto> getSupplierNotImporters();
}
