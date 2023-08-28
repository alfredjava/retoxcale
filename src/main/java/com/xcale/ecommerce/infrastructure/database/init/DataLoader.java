package com.xcale.ecommerce.infrastructure.database.init;

import com.xcale.ecommerce.domain.Product;
import com.xcale.ecommerce.domain.User;
import com.xcale.ecommerce.infrastructure.repository.ProductRepository;
import com.xcale.ecommerce.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        var product1 =  Product.builder()
                .name("iphone13")
                .description("iphone 13")
                .price(10000.0)
                .stock(10)
                .build();
        var product2 =  Product.builder()
                .name("iphone14")
                .description("iphone 14")
                .price(10000.0)
                .stock(20)
                .build();


        productRepository.saveAll(Arrays.asList(product1,product2));
        //user
        var user1 = User.builder()
                .password("pasword")
                .email("alfredfis@gmail.com").build();

        userRepository.createUser(user1);
    }
}
