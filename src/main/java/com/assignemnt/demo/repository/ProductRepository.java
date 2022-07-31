package com.assignemnt.demo.repository;

import com.assignemnt.demo.document.Customer;
import com.assignemnt.demo.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
