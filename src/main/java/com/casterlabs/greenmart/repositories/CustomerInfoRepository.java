package com.casterlabs.greenmart.repositories;

import com.casterlabs.greenmart.model.endusers.CustomerInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface CustomerInfoRepository extends CrudRepository<CustomerInfo, String> {
    Optional<CustomerInfo> findFarmerByCustomerTelephone1(String customerTelephone1);
}
