package uz.pdp.fastfoodapp.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.fastfoodapp.exception.InvalidArgumentException;
import uz.pdp.fastfoodapp.exception.MissingFieldException;
import uz.pdp.fastfoodapp.util.annotations.Price;

public class PriceValidator implements ConstraintValidator<Price, String> {
    private boolean required;

    @Override
    public void initialize(Price constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!required && value == null)
            return true;
        if (value == null)
            throw new MissingFieldException(context.getDefaultConstraintMessageTemplate());
        if (value.matches("\\d+(\\.\\d{1,3})?"))
            return true;
        throw new InvalidArgumentException("Price");
    }
}
