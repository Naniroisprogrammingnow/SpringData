package com.xmldemo.car_sale_xml.repositories;


import com.xmldemo.car_sale_xml.Entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public
interface SalesRepository extends JpaRepository<Sale, Long>
{
    @Query("select s from Sale as s")
    Set<Sale> getAll();
}
