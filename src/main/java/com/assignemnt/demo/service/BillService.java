package com.assignemnt.demo.service;

import com.assignemnt.demo.document.Bill;
import com.assignemnt.demo.model.PayableBill;

import java.util.List;

public interface BillService {

    PayableBill applyDiscount(final String billId) throws Exception;

    List<Bill> getAll();
}
