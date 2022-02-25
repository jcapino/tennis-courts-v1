package com.tenniscourts.domain.guests.service;

import com.tenniscourts.domain.commons.validator.ArgumentValidator;
import com.tenniscourts.exceptions.BusinessException;
import com.tenniscourts.exceptions.DatabaseException;
import com.tenniscourts.domain.guests.model.Guest;
import com.tenniscourts.domain.guests.repository.GuestsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UpdateGuestService {

    private final GuestsRepository guestsRepository;
    private final FindGuestService findGuestService;

    public Guest run(Guest guest) {
        validateDataBeforeUpdate(guest);
        return guestsRepository.save(guest).orElseThrow(() -> new DatabaseException("Error: Failed to create new guest"));
    }

    private void validateDataBeforeUpdate(Guest guest) {
        ArgumentValidator.isRequiredField(guest.getName(), "Name");
        ArgumentValidator.isEmptyValue(guest.getName(), "Name");

        List<Guest> registeredGuests = findGuestService.findByName(guest.getName());

        if (guestExists(guest, registeredGuests)) {
            throw new BusinessException(String.format("Guest named %s already exists", guest.getName()));
        }
    }

    private Boolean guestExists(Guest guest, List<Guest> guests) {
        return guests.stream().anyMatch(registeredGuest->
                isARegisteredGuest(guest, registeredGuest)
        );
    }

    private Boolean isARegisteredGuest(Guest guest, Guest registeredGuest){
        return Boolean.FALSE.equals(guest.getId().equals(registeredGuest.getId()))
                && guest.getName().equals(registeredGuest.getName());
    }
}
