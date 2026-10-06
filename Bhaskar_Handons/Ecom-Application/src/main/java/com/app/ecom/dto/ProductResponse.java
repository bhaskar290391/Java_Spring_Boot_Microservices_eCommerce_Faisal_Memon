package com.app.ecom.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductResponse {

    private long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stockQuantity;

    private String category;

    private String imageURL;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
