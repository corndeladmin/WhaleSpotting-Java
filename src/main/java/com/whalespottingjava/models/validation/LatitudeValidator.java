package com.whalespottingjava.models.validation;

import com.whalespottingjava.models.validation.annotations.LatitudeConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LatitudeValidator implements ConstraintValidator<LatitudeConstraint, Double> {
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        // Simple validation for now
        return value != null && value >= -90 && value <= 90;
    }
}
