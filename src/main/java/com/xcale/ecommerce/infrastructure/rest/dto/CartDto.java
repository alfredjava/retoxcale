package com.xcale.ecommerce.infrastructure.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
public class CartDto {

    private Long id;
    private UserDto userDto;
    private Double total;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CartDetailsDto> cartDetailsDto;

}
