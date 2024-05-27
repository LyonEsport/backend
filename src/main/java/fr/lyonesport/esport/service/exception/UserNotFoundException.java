package fr.lyonesport.esport.service.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
