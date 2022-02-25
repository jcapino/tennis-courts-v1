package com.tenniscourts.domain.guests.service;

import com.tenniscourts.MockApplication;
import com.tenniscourts.domain.guests.model.Guest;
import com.tenniscourts.exceptions.BusinessException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest(classes = {MockApplication.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class FindGuestServiceTest {

    @Autowired
    FindGuestService findGuestService;

    @Test
    @DisplayName("It must obtain a list with two guests")
    void itMustObtainAListWithTwoGuests(){

        List<Guest> result = findGuestService.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    @DisplayName("It should find a guest filter by Id")
    void itShouldFindAGuestFilteredById(){

        Long guestId = 1L;
        Guest result = findGuestService.findById(guestId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(guestId, result.getId());
    }

    @Test
    @DisplayName("The test must throw an exception trying to query the guests filtered by an id that does not exist")
    void itShouldThrowDataValidationExceptionTryngToFindAGuestThatDoesNotExist(){

        Long guestId = -1L;
        Exception result = Assertions.assertThrows(BusinessException.class, () -> findGuestService.findById(guestId));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(String.format("The user with Id %s does not exist", guestId), result.getMessage());
    }

    @Test
    @DisplayName("It must obtain a list with one guest filtered by name ")
    void itMustObtainAListWithOneGuestFilteredByName(){

        String name = "Rafael";
        List<Guest> result = findGuestService.findByName(name);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    @DisplayName("It must obtain a list with two guests filtered by name")
    void itMustObtainAListWithTwoGuestsFilteredByName(){

        String name = "";
        List<Guest> result = findGuestService.findByName(name);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    @DisplayName("It must obtain a list with all guests filtered by null name")
    void itMustObtainAListWithAllGuestsFilteredByNullName(){

        List<Guest> result = findGuestService.findByName(null);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }
}
