package com.assignemnt.demo.controller;

import com.assignemnt.demo.dto.CartDto;
import com.assignemnt.demo.model.Cart;
import com.assignemnt.demo.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(value = {"/add", "/update"}, method = RequestMethod.POST)
    public ResponseEntity<?> addCart(@RequestBody CartDto cartDto) throws Exception {
        Cart cart = cartService.save(cartDto);
        return ResponseEntity.status(HttpStatus.OK).body(cart);
    }

    @RequestMapping(value = {"/remove"}, method = RequestMethod.DELETE)
    public ResponseEntity<?> addCart(@RequestParam("itemId") Integer itemId) throws Exception {
        cartService.removeItemFromCart(itemId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
