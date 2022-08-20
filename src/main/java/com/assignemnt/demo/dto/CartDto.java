package com.assignemnt.demo.dto;

public class CartDto {
    private Integer customerId;
    private Integer productId;
    private Integer quantity;

    public CartDto(Integer userId, Integer productId, Integer quantity) {
        this.customerId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartDto() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
