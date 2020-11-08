package com.casterlabs.greenmart.Services;

import com.casterlabs.greenmart.model.endusers.CustomerCartItems;
import com.casterlabs.greenmart.model.endusers.CustomerInfo;
import com.casterlabs.greenmart.model.endusers.CartItemStatus;
import com.casterlabs.greenmart.model.product.ProductInfo;
import com.casterlabs.greenmart.model.product.ProductItemStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ManageCustomers {

    @Autowired
    private CustomerDbServices customerDbServices;

    @Autowired
    ManageProducts manageProducts;

    public boolean addNewCustomer(CustomerInfo customerInfo) {

        return customerDbServices.addNewCustomer(customerInfo);
    }

    public boolean removeCustomer(String customerId) {

        return customerDbServices.removeCustomer(customerId);
    }

    public CustomerInfo validateCustomerLogin(CustomerInfo customerInfo){
        CustomerInfo customerDetailsFromDb = customerDbServices.getCustomerInfoByPhone(customerInfo.getCustomerId());

        if (customerDetailsFromDb != null){
            if (!StringUtils.isEmpty(customerInfo.getCustomerPassword()) && !StringUtils.isEmpty(customerDetailsFromDb.getCustomerPassword()) &&
                    (customerInfo.getCustomerPassword().equals(customerDetailsFromDb.getCustomerPassword()))){
                return customerDetailsFromDb;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }

    public boolean addProductToCart(CustomerCartItems customerCartItem) {

        if (customerCartItem != null && !StringUtils.isEmpty(customerCartItem.getCustomerId()) && !StringUtils.isEmpty(customerCartItem.getProductId())){

            boolean itemAlreadyExistCheck = customerDbServices.checkIfItemAlreadyExistInCart(customerCartItem.getCustomerId(), customerCartItem.getProductId());

            if (!itemAlreadyExistCheck) {
                ProductInfo productInfo = manageProducts.getProductInfo(customerCartItem.getProductId());
                if (productInfo != null && productInfo.getProductStatus() != null) {
                    if (productInfo.getProductStatus().equals(ProductItemStatus.IN_STOCK)) {
                        customerCartItem.setProductStatus(productInfo.getProductStatus());
                    }

                    if (productInfo.getProductMinQuantity() != null && productInfo.getProductMinQuantity() > 0) {
                        customerCartItem.setProductQuantity(productInfo.getProductMinQuantity());
                    }
                    customerCartItem.setTransactionStatus(String.valueOf(CartItemStatus.NOT_CONFIRMED));
                    return customerDbServices.addProductToCart(customerCartItem);
                } else {
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }
    }
}
