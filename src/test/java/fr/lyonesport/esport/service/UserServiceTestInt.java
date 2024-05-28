package fr.lyonesport.esport.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.lyonesport.esport.data.User;
import fr.lyonesport.esport.service.exception.UserNotFoundException;

@SpringBootTest
public class UserServiceTestInt {
    
    @Autowired
    private UserService userService;

    @Test
    void user_can_register() throws UserNotFoundException {
        // Given
        User user = new User();
        // When
        user.setId(1l);
        user.setEmail("ruben@mail.com");
        user.setPassword("Linalalalapourquoipas%*1");
        // Then
        Assertions.assertTrue(userService.checkMailFormat(user.getEmail()));
        Assertions.assertTrue(userService.checkPasswordFormat(user.getEmail()));
        assertThrows(UserNotFoundException.class, () -> userService.find(user.getEmail()));
    }
}
