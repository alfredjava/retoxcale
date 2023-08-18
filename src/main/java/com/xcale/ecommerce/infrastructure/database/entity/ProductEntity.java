package com.xcale.ecommerce.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(unique = true)
        private String name;
        private String description;
        private BigDecimal price;
        private String image;
        @Column(name = "count_in_stock")
        private Integer countInStock;
        @Column(name = "created_at")
        private LocalDateTime createdAt;
        @Column(name = "updated_at")
        private LocalDateTime updatedAt;
}
