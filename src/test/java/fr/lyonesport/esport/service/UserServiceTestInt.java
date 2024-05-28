package fr.lyonesport.esport.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import fr.lyonesport.esport.controller.UserController;
import fr.lyonesport.esport.data.User;
import fr.lyonesport.esport.service.exception.UserNotFoundException;
import fr.lyonesport.esport.service.exception.WrongFormatException;

@SpringBootTest
public class UserServiceTestInt {
    
    @Autowired
    private UserController userController;

    @Test
    void user_can_register() throws UserNotFoundException, WrongFormatException {
        // Given
        User user = new User(1L, "test@mail.com", "12345678Azery%*oui", "", "", new ArrayList<>());
        // When
        ResponseEntity<User> userEntity = userController.register(user);
        // Then
        Assertions.assertEquals(HttpStatus.OK, userEntity.getStatusCode());;
        Assertions.assertNotNull(userEntity.getBody());
    }
}
