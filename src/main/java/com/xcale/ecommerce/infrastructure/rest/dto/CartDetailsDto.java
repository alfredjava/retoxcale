package com.xcale.ecommerce.infrastructure.rest.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailsDto {
    private Long idProduct;
    private Double price;
    private Integer quantity;
    private Double total;
}