package com.casterlabs.greenmart.config;

import com.casterlabs.greenmart.Services.AwsDynamoDbServices;
import com.casterlabs.greenmart.Services.CustomerDbServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AwsDynamoDbInit {

    @Autowired
    private AwsDynamoDbServices awsDynamoDbServices;

    @Autowired
    private CustomerDbServices customerDbServices;

    @PostConstruct
    private void initTables(){
        if(awsDynamoDbServices.createProductTable()){
            System.out.println("Created Product Table");
        }
        else{
            System.out.println("Product Table Exists!");
        }

        if(awsDynamoDbServices.createFarmerTable()){
            System.out.println("Created Farmer Table");
        }
        else{
            System.out.println("Farmer Table Exists!");
        }

        if(customerDbServices.createCustomerTable()){
            System.out.println("Created Customer Table");
        }
        else{
            System.out.println("Customer Table Exists!");
        }

        if(customerDbServices.createCustomerCartItemsTable()){
            System.out.println("Created CustomerCartItems Table");
        }
        else{
            System.out.println("CustomerCartItems Table Exists!");
        }
    }
}
