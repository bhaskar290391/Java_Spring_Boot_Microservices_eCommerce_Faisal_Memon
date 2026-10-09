package com.app.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private long id;
    private String productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;
}
