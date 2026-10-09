package com.app.ecom.controller;

import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
