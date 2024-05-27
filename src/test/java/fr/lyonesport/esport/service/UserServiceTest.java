package fr.lyonesport.esport.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;

import fr.lyonesport.esport.repository.UserRepository;

class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    
    void setUp() {
        when(userRepository.findByEmail("test@mail.com")).thenReturn(createUser());
    }
    
    @Test
    void user_not_found_in_db_with_email() {
        

    }

    @Test
    void email_not_good_format() {
    
    }

    User createUser() {
        return new User();
    }
}
