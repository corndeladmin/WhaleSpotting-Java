package com.whalespottingjava.models.validation.validators;

import com.whalespottingjava.models.validation.annotations.LongitudeConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LongitudeValidator implements ConstraintValidator<LongitudeConstraint, Double> {
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value != null && value <= 180 && value >= -180;
    }
}
