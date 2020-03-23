package json_product_shop.json1.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json_product_shop.json1.utils.FileUtil;
import json_product_shop.json1.utils.FileUtilImpl;
import json_product_shop.json1.utils.ValidationUtil;
import json_product_shop.json1.utils.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

@Configuration
public class AppControllerConfiguration {

    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtilImpl();
    }


    @Bean
    public Gson gson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

     @Bean
    public FileUtil fileUtil(){
        return new FileUtilImpl();
     }


}
