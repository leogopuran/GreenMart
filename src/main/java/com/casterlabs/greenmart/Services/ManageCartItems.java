package com.casterlabs.greenmart.Services;

import com.casterlabs.greenmart.model.endusers.CustomerCartItems;
import com.casterlabs.greenmart.model.product.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageCartItems {

    @Autowired
    private CustomerDbServices customerDbServices;

    public void addProductToCart(String productId, String customerId){

    }

    public boolean removeProductFromCart(String productId, String customerId){
        return false;
    }

    public List<ProductInfo> viewProductsInCart(CustomerCartItems customerCartDetails){

        List<ProductInfo> productsInCart = null;
        productsInCart = customerDbServices.viewProductsInCart(customerCartDetails);

        return productsInCart;
    }

    public boolean updatePreviousTransaction(String productId, String customerId){
        return false;
    }
}
