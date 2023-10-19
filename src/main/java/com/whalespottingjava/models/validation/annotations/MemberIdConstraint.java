package com.whalespottingjava.models.validation.annotations;

import com.whalespottingjava.models.validation.validators.MemberIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MemberIdValidator.class)
@Target( { ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface MemberIdConstraint {
    String message() default "Longitude must be between +180 and -180";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
