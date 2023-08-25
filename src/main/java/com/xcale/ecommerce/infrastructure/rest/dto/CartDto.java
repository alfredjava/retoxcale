package com.xcale.ecommerce.infrastructure.rest.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
