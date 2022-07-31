package com.assignemnt.demo.controller;

import com.assignemnt.demo.document.Bill;
import com.assignemnt.demo.model.PayableBill;
import com.assignemnt.demo.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {
    private BillService billService;

    public DemoController(BillService billService) {
        this.billService = billService;
    }

    @RequestMapping(value = "/applyDiscount/{billId}")
    public ResponseEntity<PayableBill> applyDiscount(@PathVariable("billId") final String billId) throws Exception {
        PayableBill payableBill = billService.applyDiscount(billId);
        return ResponseEntity.status(HttpStatus.OK).body(payableBill);
    }

    @RequestMapping(value = "/bills")
    public ResponseEntity<List<Bill>> getAllBills() throws Exception {
        List<Bill> bills = billService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(bills);
    }
}
