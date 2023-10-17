package com.whalespottingjava.models.validation.validators;

import com.whalespottingjava.models.validation.annotations.MemberIdConstraint;
import com.whalespottingjava.services.MemberDetailsService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Deprecated
public class MemberIdValidator implements ConstraintValidator<MemberIdConstraint, Long> {
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return true;
    }
}
