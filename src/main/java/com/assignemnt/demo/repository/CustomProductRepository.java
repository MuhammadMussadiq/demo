package com.assignemnt.demo.repository;

import com.assignemnt.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public interface CustomProductRepository {
    Page<Product> getAllProductByFilter(Map<String, String> filters, Pageable pageable);
}
