package uz.pdp.fastfoodapp.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.fastfoodapp.exception.InvalidArgumentException;
import uz.pdp.fastfoodapp.util.annotations.Password;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        if (value.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#_$%^&+=!])(?=\\S+$).{8,}$"))
            return true;
        throw new InvalidArgumentException("Password");
    }
}

