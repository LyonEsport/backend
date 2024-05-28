package fr.lyonesport.esport.controller;

import fr.lyonesport.esport.data.User;
import fr.lyonesport.esport.repository.UserRepository;
import fr.lyonesport.esport.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class UserControllerTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserService userService = new UserService(userRepository);
    private final UserController userController = new UserController(userService);

    @BeforeEach
    void setup() {
        when(userRepository.findByEmail("test@test.com")).thenReturn(createUser());
    }

    @Test
    void verify_password_success() {
        String expectedEmail = "test@test.com";
        String expectedPassword = "password";

        ResponseEntity<User> response = userController.verifyUser(expectedEmail, expectedPassword);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedEmail, Objects.requireNonNull(response.getBody()).getEmail());
    }

    @Test
    void verify_password_failure() {
        String expectedEmail = "test@test.com";
        String expectedPassword = "test";

        ResponseEntity<User> response = userController.verifyUser(expectedEmail, expectedPassword);

        assertEquals(HttpStatus.I_AM_A_TEAPOT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void not_found_user() {
        String expectedEmail = "test@gmail.com";
        String expectedPassword = "password";

        ResponseEntity<User> response = userController.verifyUser(expectedEmail, expectedPassword);

        assertEquals(HttpStatus.I_AM_A_TEAPOT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void internal_server_error() {
        when(userRepository.findByEmail("test@test.com")).thenThrow(RuntimeException.class);
        String expectedEmail = "test@test.com";
        String expectedPassword = "password";

        ResponseEntity<User> response = userController.verifyUser(expectedEmail, expectedPassword);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    Optional<User> createUser() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("password");
        return Optional.of(user);
    }
}