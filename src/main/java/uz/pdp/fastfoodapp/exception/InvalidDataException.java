package uz.pdp.fastfoodapp.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String m){
        super("Invalid data in field(s) " + m);
    }
}
