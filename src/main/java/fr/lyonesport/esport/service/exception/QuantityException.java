package fr.lyonesport.esport.service.exception;

public class QuantityException extends IllegalArgumentException {
    public QuantityException() {
        super();
    }

    public QuantityException(String message) {
        super(message);
    }
}
