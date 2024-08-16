package uz.pdp.fastfoodapp.exception;

public class MissingFieldException extends RuntimeException {
    public MissingFieldException(String fieldName){
        super("Missing required field(s): " + fieldName);
    }
}
