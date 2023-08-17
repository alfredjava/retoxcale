package com.xcale.ecommerce.infrastructure.rest.dto;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {
    private UUID id;
    private String name;
    private String description;
    private String price;
    private Integer stock;
}
