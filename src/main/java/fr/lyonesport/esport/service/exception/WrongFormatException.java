package fr.lyonesport.esport.service.exception;

public class WrongFormatException extends Exception {
    public WrongFormatException() {
        super();
    }

    public WrongFormatException(String message) {
        super(message);
    }
}
