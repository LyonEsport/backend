package fr.lyonesport.esport.service;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import fr.lyonesport.esport.data.User;
import fr.lyonesport.esport.repository.UserRepository;
import fr.lyonesport.esport.service.exception.UserNotFoundException;

@Service
public class UserService {

    // developper la regex
    private static final String REGEX_EMAIL = "^(.+)@(\\S+)$";

    // (?=.*[A-Z]) : Vérifie qu'il y a au moins une lettre majuscule
    // (?=.*[a-z]) : Vérifie qu'il y a au moins une lettre minuscule
    // (?=.*\d) : Vérifie qu'il y a au moins un chiffre
    // (?=.*[@$!%*?&]) : Vérifie qu'il y a au moins un des symboles spéciaux suivants : @, $, !, %, *, ?, &
    // [A-Za-z\d@$!%*?&]{16,} : Vérifie que la chaîne contient uniquement les caractères spécifiés
    // (lettres majuscules et minuscules, chiffres et symboles spéciaux) et qu'elle a une longueur d'au moins 16 caractères
    private static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{16,}$";

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User find(String emailToTest) throws UserNotFoundException {
        Optional<User> user = userRepository.findByEmail(emailToTest);
        return user.orElseThrow(() -> new UserNotFoundException());
    }

    public boolean checkMailFormat(String emailToTest) {
        return Pattern.compile(REGEX_EMAIL).matcher(emailToTest).matches();
    }

    public boolean checkPasswordFormat(String passwordToTest) {
        return Pattern.compile(REGEX_PASSWORD).matcher(passwordToTest).matches();
    }
}
