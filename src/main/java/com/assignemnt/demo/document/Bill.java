package com.assignemnt.demo.document;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Bill extends BaseModel {

    @DBRef
    private Customer customer;
    @DBRef
    private List<Product> products;

    public Bill() {}

    public Bill(String objectId, Customer customer, List<Product> products) {
        super(objectId);
        this.customer = customer;
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
