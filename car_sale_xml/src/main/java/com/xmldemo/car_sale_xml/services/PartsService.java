package com.xmldemo.car_sale_xml.services;


import com.xmldemo.car_sale_xml.Entities.Part;
import dtos.PartSeedDto;

import java.util.List;

public interface PartsService {
    void seedParts(List<PartSeedDto> partSeedDtos);
    Part getRandomPart();

}
