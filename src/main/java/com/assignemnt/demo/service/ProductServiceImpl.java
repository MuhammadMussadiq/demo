package com.assignemnt.demo.service;

import com.assignemnt.demo.model.Product;
import com.assignemnt.demo.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductRepository productRepository;

    @Override
    public Page<Product> getProducts(Map<String, String> params) {
        Integer page = 0;
        try {
            if (!StringUtils.isEmpty(params.get("page"))) {
                page = Integer.valueOf(params.get("page"));
            }
        } catch (Exception exception) {
            throw new IllegalArgumentException("Invalid page value: " + params.get("page"));
        }
        Pageable pageable = PageRequest.of(page, 10);
        return productRepository.getAllProductByFilter(params, pageable);
    }
}
