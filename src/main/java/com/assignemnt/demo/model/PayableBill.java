package com.assignemnt.demo.model;

public class PayableBill {
    private Double totalAmount;
    private Double payableAmount;

    public PayableBill(Double totalAmount, Double payableAmount) {
        this.totalAmount = totalAmount;
        this.payableAmount = payableAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(Double payableAmount) {
        this.payableAmount = payableAmount;
    }
}
