package com.app.ecom.controller;

import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.entity.CartItem;
import com.app.ecom.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    @PostMapping
    public ResponseEntity<String> addToCart(
            @RequestHeader("X-User-Id") String userId,
            @RequestBody CartItemRequest cartItemRequest){

        if(!service.addToCart(userId,cartItemRequest)){
            return ResponseEntity.badRequest().body("Product Out of stock  or User not found or Product not Found");
        }

        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> fetchCartAssociatedWithUser(  @RequestHeader("X-User-Id") String userId){
        return  ResponseEntity.ok(service.fetchCartItems(Long.parseLong(userId)));
    }

    @DeleteMapping("/item/{productId}")
    public ResponseEntity<Void> deleteCartItem( @RequestHeader("X-User-Id") String userId, @PathVariable long productId){
        return  service.removeItemFromCart(userId,productId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
