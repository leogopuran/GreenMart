package com.casterlabs.greenmart.Services;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.casterlabs.greenmart.model.endusers.CustomerCartItems;
import com.google.gson.Gson;

public class CustomerCartItemsTypeConverter implements DynamoDBTypeConverter<String, CustomerCartItems> {
    @Override
    public String convert(CustomerCartItems customerCartItems) {
        String jsonString = new Gson().toJson(customerCartItems);
        return jsonString;
    }

    @Override
    public CustomerCartItems unconvert(String s) {
        CustomerCartItems customerCartItems = new Gson().fromJson(s, CustomerCartItems.class);
        return customerCartItems;
    }
}
