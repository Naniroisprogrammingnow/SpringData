package com.xmldemo.car_sale_xml.controller;

import com.xmldemo.car_sale_xml.services.*;
import dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import utils.XmlParser;

import javax.persistence.Table;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static com.xmldemo.car_sale_xml.globalConstants.GlobalConstants.*;

@Component
public class AppController implements CommandLineRunner {

    private final XmlParser xmlParser;
    private final SupplierService supplierService;
    private final PartsService partsService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;
    private Scanner scanner = new Scanner(System.in);

    @Autowired
    public AppController(XmlParser xmlParser, SupplierService supplierService, PartsService partsService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.xmlParser = xmlParser;
        this.supplierService = supplierService;
        this.partsService = partsService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedSuppliers();
//        this.partSeed();
//        this.seedCars();
//        this.seedCustomers();
//        this.seedSales();

        while (true){
            System.out.println("Enter task number (or zero to exit): ");
            String task = scanner.nextLine();
            if (task.equals("0")){
                break;
            }else if (task.equals("1")){
                List<CustomerWithBirthDateDto> customersWithBirthdays = 
                        this.customerService.getCustomersWithBirthdays();
                CustomerWithBirthDateRootDto dto = new CustomerWithBirthDateRootDto(customersWithBirthdays);
                xmlParser.marshalToFile("src/main/resources/exercises/ex1.xml", dto);
            }else if (task.equals("2")){
                List<CarByMakeDto> toyotas = this.carService.getCarsByMake("Toyota");
                CarByMakeRootDto dto = new CarByMakeRootDto(toyotas);
                this.xmlParser.marshalToFile("src/main/resources/exercises/ex2.xml", dto);
            }else if (task.equals("3")){
                List<SupplierNotImporterDto> supplierNotImporters = this.supplierService.getSupplierNotImporters();
                SupplierNotImporterRootDto dto = new SupplierNotImporterRootDto(supplierNotImporters);
                this.xmlParser.marshalToFile("src/main/resources/exercises/ex3.xml", dto);
            }else if (task.equals("4")){
                List<CarWithPartsDto> carsWithParts = this.carService.getCarsWithParts();
                System.out.println();
                CarsWithPartsRootDto dto = new CarsWithPartsRootDto(carsWithParts);
                this.xmlParser.marshalToFile("src/main/resources/exercises/ex4.xml", dto);
            }else if (task.equals("5")){
                Set<CustomerWithCarBoughtDto> totalCustomers = this.customerService.getCustomersWithCarBought();
                CustomersWithCarBoughtRootDto dto = new CustomersWithCarBoughtRootDto(totalCustomers);
                this.xmlParser.marshalToFile("src/main/resources/exercises/ex5.xml", dto);
            }else if (task.equals("6")){
                Set<SaleDetailsDto> salesDetails = this.saleService.getSalesDetails();
                SaleDetailsRootDto dto = new SaleDetailsRootDto(salesDetails);
                this.xmlParser.marshalToFile("src/main/resources/exercises/ex6.xml", dto);

            }else {
                System.out.println("Invalid task number!");
            }
        }
    }

    private void seedSales() {
        this.saleService.seedSales();
    }

    private void seedCustomers() throws JAXBException, FileNotFoundException {
        CustomerSeedRootDto customerSeedRootDto = this.xmlParser
                .unmarshalFromFile(CUSTOMER_FILE_PATH, CustomerSeedRootDto.class);
        this.customerService.seedCustomer(customerSeedRootDto.getCustomers());
    }

    private void seedCars() throws JAXBException, FileNotFoundException {
        CarSeedRootDto carSeedRootDto = this.xmlParser.unmarshalFromFile(CAR_FILE_PATH, CarSeedRootDto.class);
        this.carService.seedCars(carSeedRootDto.getCars());
    }

    private void partSeed() throws JAXBException, FileNotFoundException {
        PartSeedRootDto partRootSeedDto = this.xmlParser
                .unmarshalFromFile(PARTS_FILE_PATH, PartSeedRootDto.class);
        this.partsService.seedParts(partRootSeedDto.getParts());
    }

    private void seedSuppliers() throws JAXBException, FileNotFoundException {
        SupplierSeedRootDto supplierSeedRootDto = this.xmlParser.unmarshalFromFile(SUPPLIER_FILE_PATH, SupplierSeedRootDto.class);
        this.supplierService.seedSuppliers(supplierSeedRootDto.getSuppliers());
    }
}
