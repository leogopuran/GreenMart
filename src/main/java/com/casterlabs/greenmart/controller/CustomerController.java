package com.casterlabs.greenmart.controller;

import com.casterlabs.greenmart.Services.ManageCartItems;
import com.casterlabs.greenmart.Services.ManageCustomers;
import com.casterlabs.greenmart.model.endusers.CustomerCartItems;
import com.casterlabs.greenmart.model.endusers.CustomerInfo;
import com.casterlabs.greenmart.model.product.ProductInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    ManageCustomers manageCustomers;

    @Autowired
    ManageCartItems manageCartItem;

    @RequestMapping(method = POST,path = "/customerSignUp")
    public boolean addNewCustomer(@RequestBody CustomerInfo customerInfo){
        if (customerInfo != null){
            boolean customerAdded = manageCustomers.addNewCustomer(customerInfo);
            return customerAdded;
        }
        return false;
    }

    @RequestMapping(method = POST,path = "/validateCustomerLogin")
    public String validateCustomerLogin(@RequestBody CustomerInfo customerInfo){
        if (customerInfo != null){
            CustomerInfo customerDetailsFromDb = manageCustomers.validateCustomerLogin(customerInfo);
            if (customerDetailsFromDb != null) {
                return new Gson().toJson(customerDetailsFromDb);
            }
            else{
                return null;
            }
        }
        else {
            return null;
        }
    }

    @RequestMapping(method = POST, path = "/addProductToCart")
    public String addProductToCart(@RequestBody CustomerCartItems customerCartItem){
        boolean addProductToCartStatus = manageCustomers.addProductToCart(customerCartItem);
        return String.valueOf(addProductToCartStatus);
    }

    @RequestMapping(method = POST, path = "/viewCartItems")
    public String viewCartItems(@RequestBody CustomerCartItems customerCart){
        List<ProductInfo> cartItems = manageCartItem.viewProductsInCart(customerCart);
        if (cartItems != null) {
            return new Gson().toJson(cartItems);
        }
        else{
            return null;
        }
    }
}
