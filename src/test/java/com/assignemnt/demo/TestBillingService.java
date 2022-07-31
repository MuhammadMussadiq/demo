package com.assignemnt.demo;

import com.assignemnt.demo.document.Bill;
import com.assignemnt.demo.document.Customer;
import com.assignemnt.demo.document.Product;
import com.assignemnt.demo.model.PayableBill;
import com.assignemnt.demo.repository.BillRepository;
import com.assignemnt.demo.service.BillService;
import com.assignemnt.demo.service.BillServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TestBillingService {
    @Mock
    private BillRepository billRepository;
    private BillService billService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.billService = new BillServiceImpl(billRepository);
    }

    @Test()
    public void NoBillFoundAgainstGivenIdTest() throws Exception {
        final String INVALID_BILL_ID = "INVALID_BILL_ID";
        Exception exception = assertThrows(Exception.class, () -> billService.applyDiscount(INVALID_BILL_ID));
        System.out.println(exception.getMessage());
        assertTrue(exception.getMessage().equals("No bill found with ID: INVALID_BILL_ID"));
    }

    @Test()
    public void applyDiscountForEmployeeTest() throws Exception {
        final String BILL_ID = "asas7";
        Customer customer = new Customer("billId", "Employee", true, false, LocalDate.now());
        Product product = new Product("objectId", "Laptop", 95.0, false);
        Bill bill = new Bill(BILL_ID, customer, Arrays.asList(product));
        when(billRepository.findById(BILL_ID)).thenReturn(Optional.of(bill));
        PayableBill payableBill = billService.applyDiscount(BILL_ID);
        assertTrue(payableBill != null);
        assertEquals(payableBill.getTotalAmount(), product.getPrice());
        double expectedPayableAmount = product.getPrice() - (product.getPrice() * (0.3));
        assertEquals(payableBill.getPayableAmount(), expectedPayableAmount);
    }

    @Test()
    public void applyDiscountForAffiliatedCustomerTest() throws Exception {
        final String BILL_ID = "asas7";
        Customer customer = new Customer("billId", "Affiliated Customer", false, true, LocalDate.now());
        Product product = new Product("objectId", "Laptop", 95.0, false);
        Bill bill = new Bill(BILL_ID, customer, Arrays.asList(product));
        when(billRepository.findById(BILL_ID)).thenReturn(Optional.of(bill));
        PayableBill payableBill = billService.applyDiscount(BILL_ID);
        assertTrue(payableBill != null);
        assertEquals(payableBill.getTotalAmount(), product.getPrice());
        double expectedPayableAmount = product.getPrice() - (product.getPrice() * (0.1));
        assertEquals(payableBill.getPayableAmount(), expectedPayableAmount);
    }

    @Test()
    public void applyDiscountOnCustomerOver2YearsTest() throws Exception {
        final String BILL_ID = "asas7";
        Customer customer = new Customer("billId", "Affiliated Customer", false, false,
                LocalDate.now().withYear(2018));
        Product product = new Product("objectId", "Laptop", 95.0, false);
        Bill bill = new Bill(BILL_ID, customer, Arrays.asList(product));
        when(billRepository.findById(BILL_ID)).thenReturn(Optional.of(bill));
        PayableBill payableBill = billService.applyDiscount(BILL_ID);
        assertTrue(payableBill != null);
        assertEquals(payableBill.getTotalAmount(), product.getPrice());
        double expectedPayableAmount = product.getPrice() - (product.getPrice() * (0.05));
        assertEquals(payableBill.getPayableAmount(), expectedPayableAmount);
    }

    @Test()
    public void applyDiscountForEvery100$Test() throws Exception {
        final String BILL_ID = "asas7";
        Customer customer = new Customer("billId", "Affiliated Customer", false, false,
                LocalDate.now());
        Product product = new Product("objectId", "Laptop", 1000.0, false);
        Bill bill = new Bill(BILL_ID, customer, Arrays.asList(product));
        when(billRepository.findById(BILL_ID)).thenReturn(Optional.of(bill));
        PayableBill payableBill = billService.applyDiscount(BILL_ID);
        assertTrue(payableBill != null);
        assertEquals(payableBill.getTotalAmount(), product.getPrice());
        double expectedPayableAmount = product.getPrice() - 50;
        assertEquals(payableBill.getPayableAmount(), expectedPayableAmount);
    }

}
