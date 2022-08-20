package com.assignemnt.demo.controller;

import com.assignemnt.demo.model.Order;
import com.assignemnt.demo.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = {"/save/{customerId}"}, method = RequestMethod.POST)
    public ResponseEntity<?> addCart(@PathVariable(name = "customerId") Integer customerId) throws Exception {
        Order order = orderService.placeOrder(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

}
