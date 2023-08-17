package com.xcale.ecommerce.infrastructure.security;
/*

import com.xcale.ecommerce.application.UserUseCase;
import com.xcale.ecommerce.infrastructure.database.entity.RoleEntity;
import com.xcale.ecommerce.infrastructure.database.entity.UserEntity;
import com.xcale.ecommerce.infrastructure.model.request.SignUpRequest;
import com.xcale.ecommerce.infrastructure.model.request.SigninRequest;
import com.xcale.ecommerce.infrastructure.model.response.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserUseCase userUseCase;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = UserEntity.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(RoleEntity.USER).build();
        userUseCase.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userUseCase.findByEmail(request.getEmail());
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}

 */
