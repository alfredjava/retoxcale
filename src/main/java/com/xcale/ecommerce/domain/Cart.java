package com.xcale.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    /**{
     usuario: {
     email: "alfredfis@gmail.com"
     },
     "productos": [
     {
     name: "iphone13",
     quantity: 1
     },
     {
     name: "iphone10",
     quantity: 1
     }
     ]
     }
     */
    private User user;
    private List<CartDetails> cartDetails;
}
