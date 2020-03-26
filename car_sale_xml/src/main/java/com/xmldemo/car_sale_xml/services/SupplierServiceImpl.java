package com.xmldemo.car_sale_xml.services;

import com.xmldemo.car_sale_xml.Entities.Supplier;
import com.xmldemo.car_sale_xml.repositories.SupplierRepository;
import dtos.SupplierNotImporterDto;
import dtos.SupplierSeedDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.ValidationUtil;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedSuppliers(List<SupplierSeedDto> supplierSeedDtos) {
        supplierSeedDtos.stream().forEach(s->{
            if (this.validationUtil.isValid(s)){
                if (this.supplierRepository.findByName(s.getName())==null){
                    Supplier supplier = this.modelMapper.map(s, Supplier.class);
                    this.supplierRepository.saveAndFlush(supplier);
                }else {
                    System.out.println(s.getName() + " supplier already exists");
                }
            }else {
                this.validationUtil.violations(s)
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }

        });
    }

    @Override
    public Supplier getRandomSupplier() {
        Random random = new Random();
        int supplierId = random.nextInt((int) this.supplierRepository.count())+1;
        return this.supplierRepository.findAllById(supplierId);
    }

    @Override
    public Supplier getSupplierId(long id) {
        return this.supplierRepository.findAllById(id);
    }

    @Override
    public List<SupplierNotImporterDto> getSupplierNotImporters() {
        List<Supplier> suppliers = this.supplierRepository.findAllByImporterFalse();
        return suppliers.stream().map(s->{
            SupplierNotImporterDto supplier = this.modelMapper.map(s, SupplierNotImporterDto.class);
            supplier.setCountParts(s.getParts().size());
            return supplier;
        }).collect(Collectors.toList());
    }
}
