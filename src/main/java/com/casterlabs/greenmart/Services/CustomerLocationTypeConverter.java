package com.casterlabs.greenmart.Services;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.casterlabs.greenmart.model.endusers.CustomerLocation;
import com.casterlabs.greenmart.model.product.ProductLocationInfo;
import com.google.gson.Gson;

public class CustomerLocationTypeConverter implements DynamoDBTypeConverter<String, CustomerLocation> {
    @Override
    public String convert(CustomerLocation customerLocation) {
        String jsonString = new Gson().toJson(customerLocation);
        return jsonString;
    }

    @Override
    public CustomerLocation unconvert(String s) {
        CustomerLocation customerLocation = new Gson().fromJson(s, CustomerLocation.class);
        return customerLocation;
    }
}
