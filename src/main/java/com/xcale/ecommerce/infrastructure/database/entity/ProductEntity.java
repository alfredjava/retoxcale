package com.xcale.ecommerce.infrastructure.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "product")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

        @Id
        private UUID id;
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
