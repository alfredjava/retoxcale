package com.xcale.ecommerce.infrastructure.config;

import com.xcale.ecommerce.application.CartServices;
import com.xcale.ecommerce.application.CartUseCase;
import com.xcale.ecommerce.application.ProductServices;
import com.xcale.ecommerce.application.ProductUseCase;
import com.xcale.ecommerce.domain.port.CartPersistencePort;
import com.xcale.ecommerce.domain.port.ProductPersistencePort;
import com.xcale.ecommerce.infrastructure.database.entity.mapper.ProductEntityMapper;
import com.xcale.ecommerce.infrastructure.database.entity.mapper.UserEntityMapper;
import com.xcale.ecommerce.infrastructure.repository.UserJpaRepository;
import com.xcale.ecommerce.infrastructure.rest.mapper.CartMapper;
import com.xcale.ecommerce.infrastructure.rest.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final UserJpaRepository userRepository;
    @Bean
    public ProductUseCase productUseCase(ProductPersistencePort productPersistencePort) {
        return new ProductServices(productPersistencePort);
    }

    @Bean
    public ProductEntityMapper productEntityMapper() {
        return ProductEntityMapper.INSTANCE;
    }

    @Bean
    public ProductMapper productMapper() {
        return ProductMapper.INSTANCE;
    }

    @Bean
    public UserEntityMapper userEntityMapper() {
        return UserEntityMapper.INSTANCE;
    }

    @Bean
    public CartUseCase cartUseCase(CartPersistencePort cartPersistencePort){
        return new CartServices(cartPersistencePort);
    }

    @Bean
    public CartMapper cartMapper(){
        return CartMapper.INSTANCE;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public UserDetailsService userDetailService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not fournd"));
    }


}
