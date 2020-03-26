package com.xmldemo.car_sale_xml.services;


import com.xmldemo.car_sale_xml.Entities.Part;
import com.xmldemo.car_sale_xml.repositories.PartsRepository;
import dtos.PartSeedDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.ValidationUtil;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Random;

@Service
public class PartsServiceImpl implements PartsService {
    private final ModelMapper modelMapper;
    private final PartsRepository partsRepository;
    private final SupplierService supplierService;
    private final ValidationUtil validationUtil;

    @Autowired
    public PartsServiceImpl(ModelMapper modelMapper, PartsRepository partsRepository, SupplierService supplierService, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.partsRepository = partsRepository;
        this.supplierService = supplierService;
        this.validationUtil = validationUtil;
    }


    @Override
    public void seedParts(List<PartSeedDto> partSeedDtos) {
        if (this.partsRepository.count()!=0){
            return;
        }
        partSeedDtos.stream().forEach(p->{
            if (this.validationUtil.isValid(p)){
                Part part = this.modelMapper.map(p, Part.class);
                part.setSupplier(this.supplierService.getRandomSupplier());
                this.partsRepository.saveAndFlush(part);
            }else{
                this.validationUtil.violations(p)
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }

        });
    }

    @Override
    public Part getRandomPart() {
        Random random = new Random();
        long id = random.nextInt((int) this.partsRepository.count())+1;
        return this.partsRepository.findAllById(id);
    }
}
