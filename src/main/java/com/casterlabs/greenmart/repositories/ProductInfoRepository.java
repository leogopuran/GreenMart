package com.casterlabs.greenmart.repositories;

import com.casterlabs.greenmart.model.product.ProductInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface ProductInfoRepository extends CrudRepository<ProductInfo, String> {
    Iterable<ProductInfo> findProductsByProductOwnerId(String productOwnerId);
}
