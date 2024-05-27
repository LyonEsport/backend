package fr.lyonesport.esport.service;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import fr.lyonesport.esport.data.User;
import fr.lyonesport.esport.repository.UserRepository;
import fr.lyonesport.esport.service.exception.UserNotFoundException;

@Service
public class UserService {

    private static final String REGEX = "^(.+)@(\\S+)$";

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User find(String emailToTest) throws UserNotFoundException {
        Optional<User> user = userRepository.findByEmail(emailToTest);
        return user.orElseThrow(() -> new UserNotFoundException());
    }

    public boolean checkMailFormat(String emailToTest) {
        return Pattern.compile(REGEX).matcher(emailToTest).matches();
    }
}
