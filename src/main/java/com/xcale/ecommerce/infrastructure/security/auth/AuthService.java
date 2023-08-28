package com.xcale.ecommerce.infrastructure.security.auth;


import com.xcale.ecommerce.infrastructure.MyException;
import com.xcale.ecommerce.infrastructure.database.entity.Role;
import com.xcale.ecommerce.infrastructure.database.entity.UserEntity;
import com.xcale.ecommerce.infrastructure.repository.UserJpaRepository;
import com.xcale.ecommerce.infrastructure.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserJpaRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        }catch (org.springframework.security.authentication.BadCredentialsException b){
            log.error("Error in login bad credential");
            throw new MyException("Error in login bad credential","");
        }catch (Exception e){
            throw new MyException("Error in login: "+ e.getMessage(),"");
        }

        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();

    }

    public AuthResponse register(RegisterRequest request) {
        UserEntity user = UserEntity.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode( request.getPassword()))
            .firstname(request.getFirstname())
            .lastname(request.lastname)
            .role(Role.USER)
            .build();

        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
        
    }

}
