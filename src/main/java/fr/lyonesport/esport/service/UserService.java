package fr.lyonesport.esport.service;

import fr.lyonesport.esport.data.User;
import fr.lyonesport.esport.repository.UserRepository;
import fr.lyonesport.esport.service.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    // developper la regex
    private static final String REGEX = "^(.+)@(\\S+)$";

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User find(String emailToTest) throws UserNotFoundException {
        Optional<User> user = userRepository.findByEmail(emailToTest);
        return user.orElseThrow(UserNotFoundException::new);
    }

    public boolean checkMailFormat(String emailToTest) {
        return Pattern.compile(REGEX).matcher(emailToTest).matches();
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public User verify(String email, String password) throws UserNotFoundException, AccessDeniedException {
        User user = find(email);
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new AccessDeniedException("");
    }
}
