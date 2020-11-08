package com.casterlabs.greenmart.Services;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.casterlabs.greenmart.model.endusers.CustomerCartItems;
import com.casterlabs.greenmart.model.endusers.CustomerInfo;
import com.casterlabs.greenmart.model.endusers.FarmerInfo;
import com.casterlabs.greenmart.model.product.ProductInfo;
import com.casterlabs.greenmart.repositories.CustomerInfoRepository;
import com.casterlabs.greenmart.repositories.FarmerInfoRepository;
import com.casterlabs.greenmart.repositories.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AwsDynamoDbServices{

    @Autowired
    AmazonDynamoDB amazonDynamoDB;

    private DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private FarmerInfoRepository farmerInfoRepository;

    public boolean createProductTable(){
//        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(ProductInfo.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        return TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
    }

    public boolean createFarmerTable(){
//        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(FarmerInfo.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        return TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
    }

    public boolean saveProductinfo(ProductInfo productInfo){
        productInfo = productInfoRepository.save(productInfo);

        if (productInfo != null){
            return true;
        }
        else{
            return false;
        }
    }

    public void saveAllProductinfo(List<ProductInfo> products){
        Iterable<ProductInfo> productInfo = productInfoRepository.saveAll(products);
    }

    public ProductInfo getProductinfo(String productId){
        Optional<ProductInfo> productInfoQueried = productInfoRepository.findById(productId);
        return productInfoQueried.get();
    }

    public List<ProductInfo> getAllProductinfo(){
        Iterable<ProductInfo> productInfoQueried = productInfoRepository.findAll();
        List<ProductInfo> products = new ArrayList<>();
        
        productInfoQueried.forEach(productInfo -> {
            products.add(productInfo);
        });
        return products;
    }

    public boolean removeProduct(String productId) {
        if (!StringUtils.isEmpty(productId)){
            productInfoRepository.deleteById(productId);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean addNewFarmer(FarmerInfo farmerInfo) {
        farmerInfo = farmerInfoRepository.save(farmerInfo);

        if (farmerInfo != null){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean removeFarmer(String farmerId) {

        if (!StringUtils.isEmpty(farmerId)){
            farmerInfoRepository.deleteById(farmerId);
            return true;
        }
        else{
            return false;
        }
    }

    public FarmerInfo getFarmerInfo(String farmerId){
        Optional<FarmerInfo> farmerInfoQueried = farmerInfoRepository.findById(farmerId);
        return farmerInfoQueried.get();
    }

    public List<ProductInfo> getAllproductsAddedByFarmer(String farmerId) {

        Iterable<ProductInfo> productInfoQueried = productInfoRepository.findProductsByProductOwnerId(farmerId);
        List<ProductInfo> products = new ArrayList<>();

        productInfoQueried.forEach(productInfo -> {
            products.add(productInfo);
        });
        return products;
    }

    public FarmerInfo getFarmerInfoByPhone(String farmerPhone1) {
        try {
            return farmerInfoRepository.findFarmerByFarmerPhone1(farmerPhone1).get();
        }
        catch (Exception e){
            return null;
        }
    }
}
