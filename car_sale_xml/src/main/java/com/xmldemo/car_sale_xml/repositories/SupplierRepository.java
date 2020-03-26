package com.xmldemo.car_sale_xml.repositories;


import com.xmldemo.car_sale_xml.Entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository <Supplier, Long> {
    Supplier findAllById(long id);
    List<Supplier> findAllByImporterFalse();
    Supplier findByName(String name);
}
