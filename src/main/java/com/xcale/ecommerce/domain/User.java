package com.xcale.ecommerce.domain;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userName;
    private String password;
    private String role;
    private Long id;
}
