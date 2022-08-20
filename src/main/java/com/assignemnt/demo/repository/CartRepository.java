package com.assignemnt.demo.repository;

import com.assignemnt.demo.model.Cart;
import com.assignemnt.demo.model.Customer;
import com.assignemnt.demo.service.CartService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findCartByCustomer_Id(Integer customerId);
}
