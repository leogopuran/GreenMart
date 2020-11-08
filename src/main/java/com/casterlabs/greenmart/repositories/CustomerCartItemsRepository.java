package com.casterlabs.greenmart.repositories;

import com.casterlabs.greenmart.model.endusers.CustomerCartItems;
import com.casterlabs.greenmart.model.product.ProductInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface CustomerCartItemsRepository extends CrudRepository<CustomerCartItems, String> {

    Iterable<CustomerCartItems> findProductsAddedBycustomerId(String customerId);
}
