package com.xcale.ecommerce.infrastructure.rest.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
}
