package com.app.ecom.service;

import com.app.ecom.dto.CartItemRequest;

public interface CartService {

    public boolean addToCart(String userid, CartItemRequest cartItem);
}
