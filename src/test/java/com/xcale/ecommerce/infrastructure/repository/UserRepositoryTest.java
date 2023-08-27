package com.xcale.ecommerce.infrastructure.repository;

import com.xcale.ecommerce.domain.Cart;
import com.xcale.ecommerce.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {


    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setPassword("testpassword");
    }

    @Test
    public void testSaveUser() {
        when(userRepository.createUser(user)).thenReturn(user);

        User savedUser = userRepository.createUser(user);

        assertEquals(savedUser,user);
    }



    @Test
    public void testFindUserByUsername() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        User foundUser = userRepository.findByEmail(user.getEmail());

        assertEquals(foundUser,user);
    }


}