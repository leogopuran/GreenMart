package com.casterlabs.greenmart.Services;

import com.casterlabs.greenmart.model.product.ProductInfo;
import com.casterlabs.greenmart.model.product.ProductItemStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManageProducts {

    @Autowired
    AwsDynamoDbServices awsDynamoDbServices;

    private List<ProductInfo> products = new ArrayList<>();

    public boolean addProduct(ProductInfo product){
        product.setProductStatus(String.valueOf(ProductItemStatus.IN_STOCK));
        return awsDynamoDbServices.saveProductinfo(product);
    }

    public boolean removeProduct(String productId){

        return awsDynamoDbServices.removeProduct(productId);
    }

    public List<ProductInfo> listAllProducts(){


        List<ProductInfo> productInfoFromDb = awsDynamoDbServices.getAllProductinfo();

        return productInfoFromDb;
    }

    public void addDummyProducts() {

        for(int i=1;i<=5;i++){
            ProductInfo product = new ProductInfo();
            product.setProductId("P00" + i); //TODO Generate unique productIds
            product.setProductName("Product_" + i);
            product.setProductPrice(100+(i*10));
            product.setProductStatus("instock");
            product.setProductType("type__" + i);
            product.setProductMaxQuantity(10);
            product.setProductMinQuantity(2);
            product.setSaleStartDate("10/10/20");
            product.setSaleEndDate("10/10/20");
            product.setProductOwnerId("O_"+i);
            product.setProductDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's");
            products.add(product);
        }

    }

    public ProductInfo getProductInfo(String productId) {
        ProductInfo productInfo = awsDynamoDbServices.getProductinfo(productId);
        return productInfo;
    }
}
