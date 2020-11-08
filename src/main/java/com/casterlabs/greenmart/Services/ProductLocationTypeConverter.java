package com.casterlabs.greenmart.Services;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.casterlabs.greenmart.model.product.ProductLocationInfo;
import com.google.gson.Gson;

public class ProductLocationTypeConverter implements DynamoDBTypeConverter<String, ProductLocationInfo> {
    @Override
    public String convert(ProductLocationInfo productLocationInfo) {
        String jsonString = new Gson().toJson(productLocationInfo);
        return jsonString;
    }

    @Override
    public ProductLocationInfo unconvert(String s) {
        ProductLocationInfo productLocationInfo = new Gson().fromJson(s, ProductLocationInfo.class);
        return productLocationInfo;
    }
}
