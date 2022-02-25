package com.tenniscourts.domain.guests.service;

import com.tenniscourts.MockApplication;
import com.tenniscourts.domain.guests.model.Guest;
import com.tenniscourts.exceptions.BusinessException;
import com.tenniscourts.exceptions.DataValidationException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {MockApplication.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CreateGuestServiceTest {

    @Autowired
    CreateGuestService createGuestService;

    @Test
    @Order(1)
    void itShouldCreateAGuest() {

        Guest guest = Guest.builder()
                .name("James Rodriguez").build();

        Guest result = createGuestService.run(guest);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(guest.getName(), result.getName());
    }

    @Test
    @Order(2)
    void itShouldThrowBusinessExceptionWhenTryingToCreateAGuest() {

        Guest guest = Guest.builder()
                .name("James Rodriguez").build();

        Exception result = Assertions.assertThrows(BusinessException.class, () -> {
            createGuestService.run(guest);
        });

        Assertions.assertNotNull(result);
        Assertions.assertEquals(String.format("Guest named %s already exists", guest.getName()), result.getMessage());
    }

    @Test
    @Order(3)
    void itShouldThrowDataValidationExceptionWhenTryingToCreateAGuestWithNullName() {

        Guest guest = Guest.builder()
                .name(null).build();

        Exception result = Assertions.assertThrows(DataValidationException.class, () -> {
            createGuestService.run(guest);
        });

        Assertions.assertNotNull(result);
        Assertions.assertEquals(String.format("The value of the %s field is required", "Name"), result.getMessage());
    }

    @Test
    @Order(4)
    void itShouldThrowDataValidationExceptionWhenTryingToCreateAGuestWithEmtpyName() {

        Guest guest = Guest.builder()
                .name("").build();

        Exception result = Assertions.assertThrows(DataValidationException.class, () -> {
            createGuestService.run(guest);
        });

        Assertions.assertNotNull(result);
        Assertions.assertEquals(String.format("The value of the %s field cannot be null or empty", "Name"), result.getMessage());
    }
}
