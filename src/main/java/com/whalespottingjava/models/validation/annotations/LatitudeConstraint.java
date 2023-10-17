package com.whalespottingjava.models.validation.annotations;

import com.whalespottingjava.models.validation.validators.LatitudeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LatitudeValidator.class)
@Target( { ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface LatitudeConstraint {
    String message() default "Latitude must be between +90 and -90";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
