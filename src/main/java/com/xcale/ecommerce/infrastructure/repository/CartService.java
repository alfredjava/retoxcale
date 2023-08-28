package com.xcale.ecommerce.infrastructure.repository;

import com.xcale.ecommerce.domain.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class CartService {

    private final CartRepository cartRepository;

    @Value("${cron.in-minute-remove-cart-inactive}")
    private int timeInMinuteRemoveCartInactive;

    @Scheduled(cron = "${cron.expression}")
    public void deleteInactiveCarts() {
        log.info("Execution mode first second of each minute cron expression {}","${cron.expression}");

        LocalDateTime now = LocalDateTime.now();

        // Restar 10 minutos a la fecha y hora actual
        LocalDateTime newDateTime = now.minusMinutes(timeInMinuteRemoveCartInactive);
        try{
            List<Cart> listCart = cartRepository.findAll();

            listCart.stream().forEach(cart -> {


                if (newDateTime.compareTo(cart.getUpdatedAt()) >= 0){
                    log.info("The cart {} will be deleted due to inactivity {} in minutes",cart.getId(),timeInMinuteRemoveCartInactive);
                    // Lógica para eliminar carritos inactivos aquí
                    cartRepository.deleteById(cart.getId());
                }
            });
        }catch (Exception e){
            log.error(e.getMessage());
        }


    }
}
