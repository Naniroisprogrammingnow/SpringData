package com.xmldemo.car_sale_xml.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import utils.*;

import java.util.Random;

@Configuration
public class AppConfiguration {
    @Bean
    public FileUtil fileUtil(){
        return new FileUtilImpl();
    }

    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Gson gson(){
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting().create();
    }

    @Bean
    public Random random(){
        return new Random();
    }

    @Bean
    public XmlParser xmlParser (){
        return new XmlParserImpl();
    }
}
