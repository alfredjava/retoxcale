package com.xcale.ecommerce.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_details")
public class CartDetailsEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @ManyToOne(cascade = CascadeType.MERGE)
        @JoinColumn(name = "product_id",referencedColumnName = "id")
        private ProductEntity product;
        private Integer quantity;
        private Double price;
        private Double total;
        @Column(name = "created_at")
        private LocalDateTime createdAt;
        @Column(name = "updated_at")
        private LocalDateTime updatedAt;
        @ManyToOne
        private CartEntity cart;
}
