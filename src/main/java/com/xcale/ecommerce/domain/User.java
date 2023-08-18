package com.xcale.ecommerce.domain;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String email;
    private String password;
    private Long id;
}
