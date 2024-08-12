package uz.pdp.fastfoodapp.exception;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String message) {
        super(message + " is invalid");
    }
}
