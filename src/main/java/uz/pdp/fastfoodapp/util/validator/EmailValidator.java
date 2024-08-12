package uz.pdp.fastfoodapp.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.fastfoodapp.exception.InvalidArgumentException;
import uz.pdp.fastfoodapp.util.annotations.Email;

public class EmailValidator implements ConstraintValidator<Email, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        if (value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            return true;
        throw new InvalidArgumentException("Email");
    }
}
