package fr.lyonesport.esport.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.lyonesport.esport.data.User;
import fr.lyonesport.esport.repository.UserRepository;

class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserService userService;

    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @BeforeEach
    void setUp() {
        when(userRepository.findByEmail("test@mail.com")).thenReturn(createUser());
    }

    @Test
    void user_found_in_db_with_email() {
        String emailToTest = "test@mail.com";
        User user = userService.find(emailToTest);
        assertNotNull(user);
        assertEquals(emailToTest, user.getEmail());
    }

    @Test
    void email_not_good_format() {
    
    }

    Optional<User> createUser() {
        return Optional.of(
            new User(
                1L,
                "test@mail.com",
                "",
                "",
                "",
                new ArrayList<>()
            )
        );
    }
}
