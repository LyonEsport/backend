package fr.lyonesport.esport.service.exception;

public class UserAlreadyRegisteredException extends Exception{
    public UserAlreadyRegisteredException() {
        super();
    }

    public UserAlreadyRegisteredException(String message) {
        super(message);
    }
}
