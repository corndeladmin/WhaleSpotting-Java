package com.whalespottingjava;

import com.whalespottingjava.models.database.Sighting;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SightingValidationTests {
    private Validator validator;

    @BeforeEach
    void setupEach() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testSightingLongitudeValidationHappy() {
        Sighting sighting = new Sighting(
                new Date(0L),
                0.0d,
                0.0d
        );
        sighting.setMemberId(1L);

        assertTrue(this.validator.validate(sighting).isEmpty());
    }

    @Test
    void testSightingLongitudeValidationSad() {
        Sighting sighting = new Sighting(
                new Date(0L),
                0.0d,
                181.0d
        );
        sighting.setMemberId(1L);

        assertFalse(this.validator.validate(sighting).isEmpty());
    }

    @Test
    void testSightingLongitudeValidationPositiveEdge() {
        Sighting sighting = new Sighting(
                new Date(0L),
                0.0d,
                180.0d
        );
        sighting.setMemberId(1L);

        assertTrue(this.validator.validate(sighting).isEmpty());
    }

    @Test
    void testSightingLongitudeValidationNegativeEdge() {
        Sighting sighting = new Sighting(
                new Date(0L),
                0.0d,
                -180.0d
        );
        sighting.setMemberId(1L);

        assertTrue(this.validator.validate(sighting).isEmpty());
    }

    @Test
    void testSightingDateValidationHappy() {
        Sighting sighting = new Sighting(
                new Date(0L),
                10.0D,
                57.0D
        );
        sighting.setMemberId(1L);

        assertTrue(this.validator.validate(sighting).isEmpty());
    }

    @Test
    void testSightingDateValidationSad() {
        Sighting sighting = new Sighting(
                new Date(1697534624526L * 2),
                10.0D,
                57.0D
        );
        sighting.setMemberId(1L);

        assertFalse(this.validator.validate(sighting).isEmpty());
    }

    // Not very accurate as the validation will probably take more than 1ms making this
        // not a real edge case
    @Test
    void testSightingDateValidationPositiveEdge() {
        Sighting sighting = new Sighting(
                new Date(System.currentTimeMillis()),
                0.0d,
                0.0d
        );
    }

    @Test
    void testSightingLatitudeValidationHappy() {
        Sighting sighting = new Sighting(
                new Date(0L),
                10.0d,
                20.0d
        );
        sighting.setMemberId(1L);

        assertTrue(this.validator.validate(sighting).isEmpty());
    }

    @Test
    void testSightingLatitudeValidationPositiveEdge() {
        Sighting sighting = new Sighting(
                new Date(0L),
                90.0d,
                0.0d
        );
        sighting.setMemberId(1L);

        assertTrue(this.validator.validate(sighting).isEmpty());
    }

    @Test
    void testSightingLatitudeValidationNegativeEdge() {
        Sighting sighting = new Sighting(
                new Date(0L),
                -90.0d,
                0.0d
        );
        sighting.setMemberId(1L);

        assertTrue(this.validator.validate(sighting).isEmpty());
    }

    @Test
    void testSightingLatitudeValidationSad() {
        Sighting sighting = new Sighting(
                new Date(0L),
                91.0d,
                20.0d
        );
        sighting.setMemberId(1L);

        assertFalse(this.validator.validate(sighting).isEmpty());
    }
}
