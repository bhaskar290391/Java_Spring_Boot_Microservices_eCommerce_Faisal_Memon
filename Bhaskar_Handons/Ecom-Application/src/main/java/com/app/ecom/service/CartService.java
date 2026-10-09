package com.app.ecom.service;

import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.entity.CartItem;

import java.util.List;

public interface CartService {

    public boolean addToCart(String userid, CartItemRequest cartItem);

    public List<CartItem> fetchCartItems(long UserId);

    public boolean removeItemFromCart(String userid,long productId);
}
