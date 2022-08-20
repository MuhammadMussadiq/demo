package com.assignemnt.demo.service;

import com.assignemnt.demo.model.Product;
import org.springframework.data.domain.Page;

import java.util.Map;


public interface ProductService {
    Page<Product> getProducts(Map<String, String> params);
}
