package com.xcale.ecommerce.infrastructure.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CartDetailsDto {
    private String product;
    private Double price;
    private Integer quantity;
    private Double total;
}
