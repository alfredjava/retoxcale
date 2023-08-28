package com.xcale.ecommerce.infrastructure.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserDto {
    private String userName;
    private Long id;
}
