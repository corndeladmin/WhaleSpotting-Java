package com.whalespottingjava;

import com.whalespottingjava.controllers.ApiController;
import com.whalespottingjava.models.database.Sighting;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SightingValidationTests {
    private ApiController apiController;

    @Test
    void testBulkDataUploadValidationHappy() {
        Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();
        Sighting sighting = new Sighting(
                new Date(0L),
                100.0D,
                57.0D
        );
        sighting.setMemberId(1L);

        assertTrue(validator.validate(sighting).isEmpty());
    }
    @Test
    // Will fail if there is no validation on the object
    void testBulkDataUploadValidationSad() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();;
        Sighting sighting = new Sighting(
                new Date(1697534624526L * 2),
                100.0d,
                200.0d
        );
        sighting.setMemberId(1L);

        assertFalse(validator.validate(sighting).isEmpty());
    }
}
