package com.xcale.ecommerce.infrastructure.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CartService {
    private static final long INACTIVITY_THRESHOLD = 1 * 60 * 1000; // 10 minutes in milliseconds


    private final CartJpaRepository cartJpaRepository;
    @Scheduled(fixedDelay = INACTIVITY_THRESHOLD)
    public void deleteInactiveCarts() {


        LocalDateTime now = LocalDateTime.now();

        // Restar 10 minutos a la fecha y hora actual
        LocalDateTime newDateTime = now.minusMinutes(10);



        cartJpaRepository.findAll().stream().map(cartEntity -> {


            if (newDateTime.compareTo(cartEntity.getUpdatedAt()) <= 0){
                // Lógica para eliminar carritos inactivos aquí
                cartJpaRepository.delete(cartEntity);
            }

            return null;
        });

    }
}
