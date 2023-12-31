package com.xcale.ecommerce.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private Long id;
    private Long idUser;
    private String userName;
    private Double total;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CartDetails> cartDetails;


}
