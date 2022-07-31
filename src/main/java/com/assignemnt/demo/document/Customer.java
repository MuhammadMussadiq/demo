package com.assignemnt.demo.document;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;


@Document
public class Customer extends BaseModel {
    private String name;
    private Boolean isStoreEmployee;
    private Boolean hasAffiliation;
    private LocalDate registrationDate;

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Customer() {
    }

    public Customer(String objectId, String name, Boolean isStoreEmployee, Boolean hasAffiliation, LocalDate regDate) {
        super(objectId);
        this.name = name;
        this.isStoreEmployee = isStoreEmployee;
        this.hasAffiliation = hasAffiliation;
        this.registrationDate = regDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStoreEmployee() {
        return isStoreEmployee;
    }

    public void setStoreEmployee(Boolean storeEmployee) {
        isStoreEmployee = storeEmployee;
    }

    public Boolean getHasAffiliation() {
        return hasAffiliation;
    }

    public void setHasAffiliation(Boolean hasAffiliation) {
        this.hasAffiliation = hasAffiliation;
    }
}
