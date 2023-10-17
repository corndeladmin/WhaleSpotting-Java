package com.whalespottingjava.models.validation.annotations;

import com.whalespottingjava.models.validation.validators.LongitudeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LongitudeValidator.class)
@Target( { ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface LongitudeConstraint {
    String message() default "Longitude must be between +180 and -180";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
