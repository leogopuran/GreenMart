package com.casterlabs.greenmart.Services;

import com.casterlabs.greenmart.model.endusers.FarmerInfo;
import com.casterlabs.greenmart.model.product.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ManageFarmers {

    @Autowired
    AwsDynamoDbServices awsDynamoDbServices;

    public boolean addNewFarmer(FarmerInfo farmerInfo){
        return awsDynamoDbServices.addNewFarmer(farmerInfo);
    }

    public boolean removeFarmer(String farmerId){
        return awsDynamoDbServices.removeFarmer(farmerId);
    }

    public List<ProductInfo> getProductsAddedByFarmer(String farmerId){
        return awsDynamoDbServices.getAllproductsAddedByFarmer(farmerId);
    }

    public FarmerInfo getFarmerInfo(String farmerId){
        return awsDynamoDbServices.getFarmerInfo(farmerId);
    }

    public FarmerInfo validateFarmerLogin(FarmerInfo farmerInfo) {
        FarmerInfo farmerDetailsFromDb = awsDynamoDbServices.getFarmerInfoByPhone(farmerInfo.getFarmerPhone1());

        if (farmerDetailsFromDb != null){
            if (!StringUtils.isEmpty(farmerInfo.getFarmerPassword()) && !StringUtils.isEmpty(farmerDetailsFromDb.getFarmerPassword()) &&
                    (farmerInfo.getFarmerPassword().equals(farmerDetailsFromDb.getFarmerPassword()))){
                return farmerDetailsFromDb;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }
}
