package com.xmldemo.car_sale_xml.repositories;

import com.xmldemo.car_sale_xml.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findById(long id);
    @Query("select c from Customer as c order by c.birthDate asc, c.youndDriver asc")
    List<Customer> customersByBirthDay();
    @Query ("select c from Customer as c where c.bought is not empty order by c.bought.size desc ")
    Set<Customer> findAllByBoughtNotNullOderOrderByBought();
}
