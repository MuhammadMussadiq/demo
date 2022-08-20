package com.assignemnt.demo.service;

import com.assignemnt.demo.dto.CartDto;
import com.assignemnt.demo.model.Cart;
import com.assignemnt.demo.model.CartItem;
import com.assignemnt.demo.model.Customer;
import com.assignemnt.demo.model.Product;
import com.assignemnt.demo.repository.CartItemRepository;
import com.assignemnt.demo.repository.CartRepository;
import com.assignemnt.demo.repository.CustomerRepository;
import com.assignemnt.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private CartItemRepository cartItemRepository;

    public CartServiceImpl(CartRepository cartRepository, CustomerRepository customerRepository,
                           ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Cart save(CartDto cartDto) {
        Optional<Customer> customerOptional = customerRepository.findById(cartDto.getCustomerId());
        Customer customer = customerOptional.orElseThrow(() ->
                new IllegalArgumentException("No customer exist with id: " + cartDto.getCustomerId()));
        Optional<Product> productOptional = productRepository.findById(cartDto.getProductId());
        Product product = productOptional.orElseThrow(() ->
                new IllegalArgumentException("No product exist with id: " + cartDto.getProductId()));
        Optional<Cart> cartOptional = cartRepository.findCartByCustomer_Id(cartDto.getCustomerId());
        Cart cart = new Cart(customer, new ArrayList<>(), 0.0);
        if (cartOptional.isPresent()) {
            cart = cartOptional.get();
        }
        cart.setCustomer(customer);
        CartItem cartItem = new CartItem(cart, product, cartDto.getQuantity());
        List<CartItem> validItems = new ArrayList<>();
        for (CartItem item : cart.getCartItems()) {
            if (item.getProduct().getId() == cartDto.getProductId()) {
                cartItem.setId(item.getId());
                cartItem.setQuantity(cartDto.getQuantity());
            } else {
                validItems.add(item);
            }
        }
        validItems.add(cartItem);
        cart.setCartItems(validItems);

        double totalCost = validItems.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
        cart.setTotalCost(totalCost);

        return cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Integer itemId) {
        Optional<CartItem> byId = cartItemRepository.findById(itemId);
        CartItem cartItem = byId.orElseThrow(() -> new IllegalArgumentException("No cart item exist with id: " + itemId));
        cartItemRepository.delete(cartItem);
    }
}
