package com.assignemnt.demo.repository;

import com.assignemnt.demo.document.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
