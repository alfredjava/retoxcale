package com.xcale.ecommerce.infrastructure.rest.dto;

import lombok.*;

import java.util.List;
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDto {
    private List<CartDetailsDto> cartDetails;
}
