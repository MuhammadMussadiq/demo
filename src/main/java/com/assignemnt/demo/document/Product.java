package com.assignemnt.demo.document;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product extends BaseModel{

    private String name;
    private Double price;
    private Boolean isGroceryItem;

    public Product() {
    }

    public Product(String objectId, String name, Double price, Boolean isGroceryItem) {
        super(objectId);
        this.name = name;
        this.price = price;
        this.isGroceryItem = isGroceryItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getGroceryItem() {
        return isGroceryItem;
    }

    public void setGroceryItem(Boolean groceryItem) {
        isGroceryItem = groceryItem;
    }
}
