package fr.lyonesport.esport.service;

import java.util.ArrayList;

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
        User user = new User(1L, "ruben@mail.com", "ruben", "jallifier", "ruben", new ArrayList<>());
        // When
        userService.find(user.getEmail());
        userService.checkMailFormat(user.getEmail());
        userService.checkPasswordFormat(user.getPassword());
        // Then
        Assertions
    }
}
