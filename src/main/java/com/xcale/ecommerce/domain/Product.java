package com.xcale.ecommerce.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Product {
    private UUID id;
    private String name;
    private String description;

    private Integer stock;
}
