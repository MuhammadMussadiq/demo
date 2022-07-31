package com.assignemnt.demo.config;

import com.assignemnt.demo.document.Bill;
import com.assignemnt.demo.document.Customer;
import com.assignemnt.demo.document.Product;
import com.assignemnt.demo.repository.BillRepository;
import com.assignemnt.demo.repository.CustomerRepository;
import com.assignemnt.demo.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DummyDataCreator implements CommandLineRunner {
    Logger LOG = LoggerFactory.getLogger(DummyDataCreator.class);

    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private BillRepository billRepository;

    public DummyDataCreator(CustomerRepository userRepository, ProductRepository productRepository, BillRepository billRepository) {
        this.customerRepository = userRepository;
        this.productRepository = productRepository;
        this.billRepository = billRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("-----Saving dummy data in database-----");
        saveDummyCustomers();
        saveDummyProducts();
        saveDummyBills();
        LOG.info("-----Dummy data saved in database-----");
    }

    private void saveDummyCustomers() {

        List<Customer> customers = Arrays.asList(new Customer("62e64f01807ac164c11ae853", "John", true, true, LocalDate.now().minusYears(4)),
                new Customer("62e64f01807ac164c11ae84f", "Adam", true, false, LocalDate.now().minusYears(4)),
                new Customer("62e64f01807ac164c11ae851", "Ibraheem", false, true, LocalDate.now()),
                new Customer("62e64f01807ac164c11ae852", "Asad", false, false, LocalDate.now()));
        customerRepository.saveAll(customers);
    }

    private void saveDummyProducts() {

        List<Product> products = Arrays.asList(new Product("62e64f01807ac164c11ae855", "Bread", 10.0, true),
                new Product("62e64f01807ac164c11ae856", "Bread", 10.0, true),
                new Product("62e64f01807ac164c11ae857", "Laptop", 1000.0, false),
                new Product("62e64f01807ac164c11ae858", "Mobile", 100.0, false),
                new Product("62e64f01807ac164c11ae859", "Chicken", 50.0, true),
                new Product("62e64f01807ac164c11ae860", "Rice", 20.0, true),
                new Product("62e64f01807ac164c11ae861", "LED TV", 500.0, false),
                new Product("62e64f01807ac164c11ae862", "LED Bulb", 40.0, false));
        productRepository.saveAll(products);
    }

    private void saveDummyBills() {
        List<Bill> bills = Arrays.asList(getBill1(), getBill2(), getBill3(), getBill4());
        billRepository.saveAll(bills);
    }

    private Bill getBill1() {
        return new Bill("62e64f01807ac164c11ae990", getCustomerWithId("62e64f01807ac164c11ae84f"),
                Arrays.asList(getProductWithId("62e64f01807ac164c11ae856"),
                        getProductWithId("62e64f01807ac164c11ae860"),
                        getProductWithId("62e64f01807ac164c11ae862")));
    }

    private Bill getBill2() {
        return new Bill("62e64f01807ac164c11ae991", getCustomerWithId("62e64f01807ac164c11ae851"),
                Arrays.asList(getProductWithId("62e64f01807ac164c11ae857"),
                        getProductWithId("62e64f01807ac164c11ae858"),
                        getProductWithId("62e64f01807ac164c11ae861")));
    }

    private Bill getBill3() {
        return new Bill("62e64f01807ac164c11ae992", getCustomerWithId("62e64f01807ac164c11ae852"),
                Arrays.asList(getProductWithId("62e64f01807ac164c11ae860"),
                        getProductWithId("62e64f01807ac164c11ae856"),
                        getProductWithId("62e64f01807ac164c11ae859")));
    }

    private Bill getBill4() {
        return new Bill("62e64f01807ac164c11ae995", getCustomerWithId("62e64f01807ac164c11ae853"),
                Arrays.asList(getProductWithId("62e64f01807ac164c11ae855"),
                        getProductWithId("62e64f01807ac164c11ae856"),
                        getProductWithId("62e64f01807ac164c11ae857"),
                        getProductWithId("62e64f01807ac164c11ae858"),
                        getProductWithId("62e64f01807ac164c11ae859"),
                        getProductWithId("62e64f01807ac164c11ae860"),
                        getProductWithId("62e64f01807ac164c11ae861"),
                        getProductWithId("62e64f01807ac164c11ae862")));
    }

    private Customer getCustomerWithId(String id) {
        Customer customer = new Customer();
        customer.setObjectId(id);
        return customer;
    }

    private Product getProductWithId(String id) {
        Product product = new Product();
        product.setObjectId(id);
        return product;
    }
}
