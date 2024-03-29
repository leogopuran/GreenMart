package com.casterlabs.greenmart.model.product;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.casterlabs.greenmart.Services.ProductLocationTypeConverter;

@DynamoDBTable(tableName = "ProductInfo")
public class ProductInfo {

    private String productId;
    private String productName;
    private String productType;
    private Integer productPrice;
    private Integer productMaxQuantity;
    private Integer productMinQuantity;
    private String productStatus;
    private String saleStartDate;
    private String saleEndDate;
    private ProductLocationInfo productLocationInfo;
    private String productOwnerId;
    private String productDescription;
    private String productAddDate;

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @DynamoDBAttribute
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @DynamoDBAttribute
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @DynamoDBAttribute
    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    @DynamoDBAttribute
    public Integer getProductMaxQuantity() {
        return productMaxQuantity;
    }

    public void setProductMaxQuantity(Integer productMaxQuantity) {
        this.productMaxQuantity = productMaxQuantity;
    }

    @DynamoDBAttribute
    public Integer getProductMinQuantity() {
        return productMinQuantity;
    }

    public void setProductMinQuantity(Integer productMinQuantity) {
        this.productMinQuantity = productMinQuantity;
    }

    @DynamoDBAttribute
    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    @DynamoDBAttribute
    public String getSaleStartDate() {
        return saleStartDate;
    }

    public void setSaleStartDate(String saleStartDate) {
        this.saleStartDate = saleStartDate;
    }

    @DynamoDBAttribute
    public String getSaleEndDate() {
        return saleEndDate;
    }

    public void setSaleEndDate(String saleEndDate) {
        this.saleEndDate = saleEndDate;
    }

    @DynamoDBTypeConverted(converter = ProductLocationTypeConverter.class)
    @DynamoDBAttribute
    public ProductLocationInfo getProductLocationInfo() {
        return productLocationInfo;
    }

    public void setProductLocationInfo(ProductLocationInfo productLocationInfo) {
        this.productLocationInfo = productLocationInfo;
    }

    @DynamoDBAttribute
    public String getProductOwnerId() {
        return productOwnerId;
    }

    public void setProductOwnerId(String productOwnerId) {
        this.productOwnerId = productOwnerId;
    }

    @DynamoDBAttribute
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @DynamoDBAttribute
    public String getProductAddDate() {
        return productAddDate;
    }

    public void setProductAddDate(String productAddDate) {
        this.productAddDate = productAddDate;
    }
}
