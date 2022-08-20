package com.assignemnt.demo.controller;

import com.assignemnt.demo.model.Product;
import com.assignemnt.demo.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService demoService) {
        this.productService = demoService;
    }

    @RequestMapping(value = "/all")
    public ResponseEntity<?> getProducts(@RequestParam Map<String, String> params) throws Exception {
        Page<Product> products = productService.getProducts(params);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
}
