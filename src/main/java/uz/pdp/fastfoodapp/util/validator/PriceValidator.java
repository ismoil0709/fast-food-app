package uz.pdp.fastfoodapp.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.fastfoodapp.exception.InvalidArgumentException;
import uz.pdp.fastfoodapp.util.annotations.Price;

public class PriceValidator implements ConstraintValidator<Price, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        if (value.matches("\\d+(\\.\\d{1,3})?"))
            return true;
        throw new InvalidArgumentException("Price");
    }
}
