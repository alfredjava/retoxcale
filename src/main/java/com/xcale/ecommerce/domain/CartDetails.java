package com.xcale.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDetails {
    private Long idProduct;
    private Double price;
    private Integer quantity;
    private Double total;
}
