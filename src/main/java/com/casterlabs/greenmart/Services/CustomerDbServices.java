package com.casterlabs.greenmart.Services;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.casterlabs.greenmart.model.endusers.CustomerCartItems;
import com.casterlabs.greenmart.model.endusers.CustomerInfo;
import com.casterlabs.greenmart.model.product.ProductInfo;
import com.casterlabs.greenmart.repositories.CustomerCartItemsRepository;
import com.casterlabs.greenmart.repositories.CustomerInfoRepository;
import com.casterlabs.greenmart.repositories.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerDbServices {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private CustomerInfoRepository customerInfoRepository;

    @Autowired
    private CustomerCartItemsRepository customerCartItemsRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private CustomerDbServiceHelper customerDbServiceHelper;

    private DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

    public boolean createCustomerTable(){
        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(CustomerInfo.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        return TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
    }

    public boolean createCustomerCartItemsTable() {
        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(CustomerCartItems.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        return TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
    }

    public boolean addNewCustomer(CustomerInfo customerInfo) {
        customerInfo = customerInfoRepository.save(customerInfo);

        if (customerInfo != null){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean removeCustomer(String customerId) {
        if (!StringUtils.isEmpty(customerId)){
            customerInfoRepository.deleteById(customerId);
            return true;
        }
        else{
            return false;
        }
    }

    public CustomerInfo getCustomerInfoByPhone(String customerTelephone1) {
        try {
            return customerInfoRepository.findFarmerByCustomerTelephone1(customerTelephone1).get();
        }
        catch (Exception e){
            return null;
        }
    }

    public boolean addProductToCart(CustomerCartItems customerCartItem) {

        CustomerCartItems customerCartItemAdded = null;
        if (customerCartItem != null){
            customerCartItemAdded = customerCartItemsRepository.save(customerCartItem);
        }

        if (customerCartItemAdded != null){
            return true;
        }
        else{
            return false;
        }
    }

    public List<ProductInfo> viewProductsInCart(CustomerCartItems customerCartDetails) {

        Iterable<CustomerCartItems> cartItemsQueried = customerCartItemsRepository.findProductsAddedBycustomerId(customerCartDetails.getCustomerId());;
        List<ProductInfo> productsInCart = new ArrayList<>();

        if (cartItemsQueried != null){
            cartItemsQueried.forEach(cartItem -> {
                Optional<ProductInfo> productInfo = productInfoRepository.findById(cartItem.getProductId());
                productsInCart.add(productInfo.get());
            });
        }

        return productsInCart;
    }

    public boolean checkIfItemAlreadyExistInCart(String customerId, String productId) {

        boolean productAlreadyExistStatus = true;

        if (!StringUtils.isEmpty(customerId)){
            productAlreadyExistStatus = customerDbServiceHelper.checkIfProductAlreadyExist(customerId, productId);
            return productAlreadyExistStatus;
        }
        else {
            return productAlreadyExistStatus;
        }

    }
}
