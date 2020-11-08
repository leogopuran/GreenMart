package com.casterlabs.greenmart.repositories;

import com.casterlabs.greenmart.model.endusers.FarmerInfo;
import com.casterlabs.greenmart.model.product.ProductInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface FarmerInfoRepository extends CrudRepository<FarmerInfo, String> {
    Optional<FarmerInfo> findFarmerByFarmerPhone1(String farmerPhone1);
}
