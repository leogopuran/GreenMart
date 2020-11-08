package com.casterlabs.greenmart.controller;

import com.casterlabs.greenmart.Services.ManageFarmers;
import com.casterlabs.greenmart.Services.ManageProducts;
import com.casterlabs.greenmart.model.endusers.FarmerInfo;
import com.casterlabs.greenmart.model.product.ProductInfo;
import com.google.gson.Gson;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

@RestController
@CrossOrigin(origins = "*")
public class MainController {

    @Autowired
    private ManageProducts manageProducts;

    @Autowired
    private ManageFarmers manageFarmers;

    @RequestMapping(method = RequestMethod.GET, value = "/listProducts")
    public String viewProducts(@RequestParam(required = false) String productId){

        if (!org.springframework.util.StringUtils.isEmpty(productId)){
            return new Gson().toJson(manageProducts.getProductInfo(productId));
        }
        else{
            return new Gson().toJson(manageProducts.listAllProducts());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addProduct")
    public boolean addNewProduct(@Valid @RequestBody ProductInfo productInfo){

        //TODO Validate ProductInfo
        return manageProducts.addProduct(productInfo);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addNewFarmer")
    public boolean addNewFarmer(@Valid @RequestBody FarmerInfo farmerInfo){

        //TODO Validate ProductInfo
        return manageFarmers.addNewFarmer(farmerInfo);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/viewFarmerInfo")
    public String viewFarmerInfo(@RequestParam(required = true) String farmerId){

        //TODO Validate ProductInfo
        FarmerInfo farmerInfo = manageFarmers.getFarmerInfo(farmerId);

        if (farmerInfo != null){
            return new Gson().toJson(farmerInfo);
        }
        else{
            return "No Farmer record found...!";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/validateFarmerLogin")
    public String validateFarmerLogin(@RequestBody FarmerInfo farmerInfo){

        FarmerInfo farmerDetails = manageFarmers.validateFarmerLogin(farmerInfo);

        if (farmerDetails != null){
            return new Gson().toJson(farmerDetails);
        }
        else{
            return "No Farmer record found...!";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/productsAddedByFarmer")
    public String productsAddedByFarmer(@RequestBody FarmerInfo farmerInfo){

        List<ProductInfo> productsAddedByFarmer = manageFarmers.getProductsAddedByFarmer(farmerInfo.getFarmerId());

        if (productsAddedByFarmer != null){
            return new Gson().toJson(productsAddedByFarmer);
        }
        else{
            return null;
        }
    }

    @RequestMapping(value = "/removeProduct")
    public boolean removeProduct(@RequestParam(required = true) String productId){

        //TODO Validate ProductId
        return manageProducts.removeProduct(productId);
    }
}
