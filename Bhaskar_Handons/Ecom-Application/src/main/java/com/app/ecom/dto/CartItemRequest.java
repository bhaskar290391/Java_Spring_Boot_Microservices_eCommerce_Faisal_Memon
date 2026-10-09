package com.app.ecom.dto;

import lombok.Data;

@Data
public class CartItemRequest {

    private long productId;
    private Integer quantity;

}
