package app.service;

public class CustomerCreateException extends RuntimeException {
    public CustomerCreateException(String message) {
        super(message);
    }
}
