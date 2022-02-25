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
@SpringBootTest(classes = {MockApplication.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class UpdateGuestServiceTest {

    @Autowired
    FindGuestService findGuestService;
    @Autowired
    UpdateGuestService updateGuestService;

    @Test
    @DisplayName("It should update a guest successfully")
    void itShouldUpdateAGuestSuccessfully() {

        Long guestId = 1L;

        Guest targetGuest = findGuestService.findById(guestId);
        String updatedGuestName = "Updated Name successfully";
        targetGuest.setName(updatedGuestName);

        Guest result = updateGuestService.run(targetGuest);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(updatedGuestName, result.getName());
    }

    @Test
    @DisplayName("It should fail when trying to update a guest that is already registered")
    void itShouldFailWhenTryngToUpdateAGuestThatIsAlreadyRegistered() {

        Long guestId = 1L;

        Guest targetGuest = findGuestService.findById(guestId);
        targetGuest.setName("Rafael Nadal");

        Exception result = Assertions.assertThrows(BusinessException.class, () -> updateGuestService.run(targetGuest));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(String.format("Guest named %s already exists", targetGuest.getName()), result.getMessage());
    }

    @Test
    @DisplayName("It should throw a exception when trying to update a guest with null name")
    void itShouldFailWhenTryngToUpdateAGuestWithNullName() {

        Long guestId = 1L;

        Guest targetGuest = findGuestService.findById(guestId);
        targetGuest.setName(null);

        Exception result = Assertions.assertThrows(DataValidationException.class, () -> updateGuestService.run(targetGuest));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(String.format("The value of the %s field is required", "Name"), result.getMessage());
    }

    @Test
    @DisplayName("It should throw a exception when trying to update a guest with empty name")
    void itShouldFailWhenTryngToUpdateAGuestWithEmptyName() {

        Long guestId = 1L;

        Guest targetGuest = findGuestService.findById(guestId);
        targetGuest.setName("");

        Exception result = Assertions.assertThrows(DataValidationException.class, () -> updateGuestService.run(targetGuest));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(String.format("The value of the %s field cannot be null or empty", "Name"), result.getMessage());
    }
}
