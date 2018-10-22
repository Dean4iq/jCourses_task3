package ua.testing.model;

public class IllegalLoginException extends RuntimeException {
    public IllegalLoginException(String message){
        super("Login exists: " + message);
    }
}
