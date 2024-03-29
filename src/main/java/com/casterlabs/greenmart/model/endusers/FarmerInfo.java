package com.casterlabs.greenmart.model.endusers;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "FarmerInfo")
public class FarmerInfo {

    private String farmerId;
    private String farmerName;
    private String farmerAddress;
    private String farmerPhone1;
    private String farmerPhone2;
    private String farmerEmail;
    private String farmerPassword;

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    @DynamoDBAttribute
    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    @DynamoDBAttribute
    public String getFarmerAddress() {
        return farmerAddress;
    }

    public void setFarmerAddress(String farmerAddress) {
        this.farmerAddress = farmerAddress;
    }

    @DynamoDBAttribute
    public String getFarmerPhone1() {
        return farmerPhone1;
    }

    public void setFarmerPhone1(String farmerPhone1) {
        this.farmerPhone1 = farmerPhone1;
    }

    @DynamoDBAttribute
    public String getFarmerPhone2() {
        return farmerPhone2;
    }

    public void setFarmerPhone2(String farmerPhone2) {
        this.farmerPhone2 = farmerPhone2;
    }

    @DynamoDBAttribute
    public String getFarmerEmail() {
        return farmerEmail;
    }

    public void setFarmerEmail(String farmerEmail) {
        this.farmerEmail = farmerEmail;
    }

    @DynamoDBAttribute
    public String getFarmerPassword() {
        return farmerPassword;
    }

    public void setFarmerPassword(String farmerPassword) {
        this.farmerPassword = farmerPassword;
    }
}
