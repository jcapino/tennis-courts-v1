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
public class DeleteGuestServiceTest {

    @Autowired
    FindGuestService findGuestService;
    @Autowired
    DeleteGuestService deleteGuestService;

    @Test
    @DisplayName("It should successfully remove a guest filtered by id")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void itShouldSuccessfullyRemoveAGuestFilteredById(){
        Long guestId = 1L;

        deleteGuestService.run(guestId);

        Exception result = Assertions.assertThrows(BusinessException.class, ()-> findGuestService.findById(guestId));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(String.format("The user with Id %s does not exist", guestId), result.getMessage());
    }

    @Test
    @DisplayName("It should successfully remove a guest filtered by id")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void itShouldSuccessfullyRemoveTwoGuestFilteredByIdEachCalledMethod(){
        Long targetGuestId1 = 1L;
        Long targetGuestId2 = 2L;

        deleteGuestService.run(targetGuestId1);
        deleteGuestService.run(targetGuestId2);

        List<Guest> result = findGuestService.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(0, result.size());
    }
}
