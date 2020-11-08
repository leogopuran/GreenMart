package com.casterlabs.greenmart;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.casterlabs.greenmart.Services.AwsDynamoDbServices;
import com.casterlabs.greenmart.Services.CustomerDbServiceHelper;
import com.casterlabs.greenmart.Services.ManageFarmers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

enum dbTableNames {
	ProductInfo,
	CustomerInfo,
	CustomerCartItems,
	FarmerInfo
}

@SpringBootTest
class GreenmartApplicationTests {

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

	@Autowired
	private AwsDynamoDbServices awsDynamoDbServices;

	@Autowired
	private  CustomerDbServiceHelper customerDbServiceHelper;

	@Test
	void functionalTests() {
		DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
		boolean productAlreadyExistStatus = true;

		String productId = "cDYWOgvlQXwiEvpbe52w";
		String customerId = "123";

		/*Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
		expressionAttributeValues.put(":customerId", new AttributeValue().withS("123"));
		expressionAttributeValues.put(":productId", new AttributeValue().withS("cDYWOgvlQXwiEvpbe52w"));

		ScanRequest scanRequest = new ScanRequest()
				.withTableName(dbTableNames.CustomerCartItems.name())
				.withFilterExpression("customerId = :customerId and productId = :productId")
				.withProjectionExpression("Id")
				.withExpressionAttributeValues(expressionAttributeValues);*/

		customerDbServiceHelper.checkIfProductAlreadyExist(customerId, productId);


	}

}
