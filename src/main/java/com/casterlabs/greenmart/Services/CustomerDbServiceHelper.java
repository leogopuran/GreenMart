package com.casterlabs.greenmart.Services;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerDbServiceHelper {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    private enum dbTableNames {
        ProductInfo,
        CustomerInfo,
        CustomerCartItems,
        FarmerInfo
    }

    public boolean checkIfProductAlreadyExist(String customerId, String productId) {

        boolean productAlreadyExistStatus;

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();

        expressionAttributeValues.put(":customerId", new AttributeValue().withS(customerId));
        expressionAttributeValues.put(":productId", new AttributeValue().withS(productId));

        ScanRequest scanRequest = new ScanRequest()
                .withTableName(dbTableNames.CustomerCartItems.name())
                .withFilterExpression("customerId = :customerId and productId = :productId")
//                .withProjectionExpression("cartItemId, transactionStatus, productId")
                .withExpressionAttributeValues(expressionAttributeValues);

        ScanResult scanResult = amazonDynamoDB.scan(scanRequest);

        scanResult.getItems();

        if (scanResult.getCount() == 0){
            productAlreadyExistStatus = false;
        }
        else{
            productAlreadyExistStatus = true;
        }
        return productAlreadyExistStatus;
    }
}
