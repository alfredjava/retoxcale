package com.xcale.ecommerce.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class CartEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Double total;
        @ManyToOne
        private UserEntity userEntity;
        @Column(name = "created_at")
        private LocalDateTime createdAt;
        @Column(name = "updated_at")
        private LocalDateTime updatedAt;
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
        private List<CartDetailsEntity> cartDetailsEntities;

}
