package fr.lyonesport.esport.service;

import fr.lyonesport.esport.data.User;
import fr.lyonesport.esport.repository.UserRepository;
import fr.lyonesport.esport.service.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserService userService = new UserService(userRepository);

    @BeforeEach
    void setUp() {
        when(userRepository.findByEmail("test@mail.com")).thenReturn(createUser());
    }

    @Test
    void user_found_in_db_with_email() throws UserNotFoundException {
        String emailToTest = "test@mail.com";
        User user = userService.find(emailToTest);
        assertNotNull(user);
        assertEquals(emailToTest, user.getEmail());
    }

    @Test
    void user_not_found_in_db_with_email() {
        String emailToTest = "test_not_found@mail.com";
        assertThrows(UserNotFoundException.class, () -> userService.find(emailToTest));
    }

    @Test
    void email_not_good_format() {
        String emailToTest = "testmailcom";
        boolean isNotGoodFormat = userService.checkMailFormat(emailToTest);
        assertFalse(isNotGoodFormat);
    }

    @Test
    void correct_password() throws UserNotFoundException, AccessDeniedException {
        String emailToTest = "test@mail.com";

        User user = userService.verify(emailToTest, "lyon");

        assertNotNull(user);
        assertEquals(emailToTest, user.getEmail());
    }

    @Test
    void insert_new_user() {
        when(userRepository.save(any(User.class))).thenReturn(createUser().get());

        userService.add(createUser().get());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void email_good_format() {
        String emailToTest = "test@mail.com";
        boolean isGoodFormat = userService.checkMailFormat(emailToTest);
        assertTrue(isGoodFormat);
    }

    @Test
    void password_not_good_format() {
        String passwordToTest = "1234";
        boolean isNotGoodFormat = userService.checkPasswordFormat(passwordToTest);
        assertFalse(isNotGoodFormat);
    }

    @Test
    void passsord_good_format() {
        String passwordToTest = "H2oCdeLO%*129283";
        boolean isGoodFormat = userService.checkPasswordFormat(passwordToTest);
        assertTrue(isGoodFormat);
    }

    Optional<User> createUser() {
        return Optional.of(
            new User(
                1L,
                "test@mail.com",
                    "lyon",
                "",
                "",
                new ArrayList<>()
            )
        );
    }
}
