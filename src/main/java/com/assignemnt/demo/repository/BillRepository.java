package com.assignemnt.demo.repository;

import com.assignemnt.demo.document.Bill;
import com.assignemnt.demo.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BillRepository extends MongoRepository<Bill, String> {
}
