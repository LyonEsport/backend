package fr.lyonesport.esport.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import fr.lyonesport.esport.controller.UserController;
import fr.lyonesport.esport.data.User;
import fr.lyonesport.esport.repository.UserRepository;
import fr.lyonesport.esport.service.exception.WrongFormatException;

@SpringBootTest
public class UserServiceTestInt {
    
    @Autowired
    private UserController userController;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        when(userRepository.findByEmail("test@mail.com")).thenReturn(createUser());
    }

    @Test
    void user_can_register() throws WrongFormatException {
        // Given
        User user = new User(1L, "test@mail.com", "12345678Azery%*oui", "", "", new ArrayList<>());
        // When
        ResponseEntity<User> userEntity = userController.register(user);
        // Then
        Assertions.assertEquals(HttpStatus.OK, userEntity.getStatusCode());;
        Assertions.assertNotNull(userEntity.getBody());
    }

    @Test
    void user_can_log_in() {
        // Given
        String email = "test@mail.com";
        String password = "12345678Azery%*oui";
        // When
        ResponseEntity<User> userEntity = userController.verifyUser(email, password);
        // Then
        Assertions.assertNotNull(userEntity.getBody());
    }

    Optional<User> createUser() {
        return Optional.of(
            new User(
                1L,
                "test@mail.com",
                "12345678Azery%*oui",
                "",
                "",
                new ArrayList<>()
            )
        );
    }
}
