package com.assignemnt.demo.service;

import com.assignemnt.demo.dto.CartDto;
import com.assignemnt.demo.model.Cart;

public interface CartService {
    Cart save(CartDto cartDto);

    void removeItemFromCart(Integer itemId);
}
