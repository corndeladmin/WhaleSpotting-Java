package com.whalespottingjava;

import com.whalespottingjava.controllers.ApiController;
import com.whalespottingjava.models.database.Sighting;
import com.whalespottingjava.repositories.SightingRepository;
import com.whalespottingjava.services.SightingService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;

@SpringBootTest
public class ApiControllerTests {
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
