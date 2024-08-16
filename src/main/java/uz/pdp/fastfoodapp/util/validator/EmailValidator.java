package uz.pdp.fastfoodapp.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.fastfoodapp.exception.InvalidArgumentException;
import uz.pdp.fastfoodapp.exception.MissingFieldException;
import uz.pdp.fastfoodapp.util.annotations.Email;

public class EmailValidator implements ConstraintValidator<Email, String> {
    private boolean required;

    @Override
    public void initialize(Email constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!required && value == null)
            return true;
        if (value == null)
            throw new MissingFieldException(context.getDefaultConstraintMessageTemplate());
        if (value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            return true;
        throw new InvalidArgumentException("Email");
    }
}
