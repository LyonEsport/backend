package fr.lyonesport.esport.controller;

import fr.lyonesport.esport.data.User;
import fr.lyonesport.esport.service.UserService;
import fr.lyonesport.esport.service.exception.UserAlreadyRegisteredException;
import fr.lyonesport.esport.service.exception.UserNotFoundException;
import fr.lyonesport.esport.service.exception.WrongFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/app/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/verify")
    public ResponseEntity<User> verifyUser(@RequestParam String email, @RequestParam String password) {
        try {
            User user = userService.verify(email, password);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (UserNotFoundException | AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws WrongFormatException, WrongFormatException {
        try {
            userService.register(user);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (UserAlreadyRegisteredException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (WrongFormatException e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
