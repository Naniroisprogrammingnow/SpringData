package com.xmldemo.car_sale_xml.repositories;


import com.xmldemo.car_sale_xml.Entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartsRepository extends JpaRepository<Part, Long> {
    Part findAllById(long id);
}
