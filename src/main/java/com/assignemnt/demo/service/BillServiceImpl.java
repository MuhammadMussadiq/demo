package com.assignemnt.demo.service;

import com.assignemnt.demo.document.Bill;
import com.assignemnt.demo.document.Product;
import com.assignemnt.demo.model.PayableBill;
import com.assignemnt.demo.repository.BillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {
    Logger LOG = LoggerFactory.getLogger(BillServiceImpl.class);

    private BillRepository billRepository;

    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public PayableBill applyDiscount(String billId) throws Exception {
        LOG.info("Applying discount on Bill with ID: {}", billId);
        Optional<Bill> billOptional = billRepository.findById(billId);
        if (!billOptional.isPresent()) {
            throw new Exception("No bill found with ID: " + billId);
        }
        Bill bill = billOptional.get();
        double totalAmount = bill.getProducts()
                .stream()
                .map(Product::getPrice)
                .mapToDouble(Double::doubleValue)
                .sum();
        double discountForEvery100$ = applyDiscountForEvery100$(totalAmount);
        double percentageDiscount = applyPercentageDiscount(bill);
        double totalDiscount = discountForEvery100$ + percentageDiscount;
        double payableAmount = totalAmount - totalDiscount;
        PayableBill payableBill = new PayableBill(totalAmount, payableAmount);
        return payableBill;
    }

    @Override
    public List<Bill> getAll() {
        return billRepository.findAll();
    }

    private double applyPercentageDiscount(Bill bill) {
        double totalAmountForItemsOtherThanGroceries = bill.getProducts()
                .stream()
                .filter(product -> !product.getGroceryItem())
                .map(Product::getPrice)
                .mapToDouble(Double::doubleValue)
                .sum();
        double discount = 0.0;
        if (bill.getCustomer().getStoreEmployee()) {
            discount = (totalAmountForItemsOtherThanGroceries * 0.3);
        } else if (bill.getCustomer().getHasAffiliation()) {
            discount = (totalAmountForItemsOtherThanGroceries * 0.1);
        } else if (ChronoUnit.YEARS.between(bill.getCustomer().getRegistrationDate(), LocalDate.now()) > 2) {
            discount = (totalAmountForItemsOtherThanGroceries * 0.05);
        }

        return discount;
    }

    private double applyDiscountForEvery100$(double totalAmount) {
        if(totalAmount < 100) {
            return 0.0;
        }
        double discount = (totalAmount / 100) * 5;
        return discount;
    }
}
