package com.tenniscourts.domain.guests.service;

import com.tenniscourts.domain.commons.validator.ArgumentValidator;
import com.tenniscourts.exceptions.BusinessException;
import com.tenniscourts.exceptions.DatabaseException;
import com.tenniscourts.domain.guests.model.Guest;
import com.tenniscourts.domain.guests.repository.GuestsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class CreateGuestService {

    private GuestsRepository guestsRepository;
    private FindGuestService findGuestService;

    public Guest run(Guest guest) {
        validateDataBeforeSave(guest);

        return guestsRepository.save(guest).orElseThrow(() -> new DatabaseException("Error: Failed to create new guest"));
    }

    private void validateDataBeforeSave(Guest guest) {
        ArgumentValidator.isRequiredField(guest.getName(), "Name");
        ArgumentValidator.isEmptyValue(guest.getName(), "Name");

        List<Guest> registeredGuests = findGuestService.findByName(guest.getName());

        if (Boolean.FALSE.equals(CollectionUtils.isEmpty(registeredGuests))) {
            if (isGuestAlreadyRegistered(guest, registeredGuests)) {
                throw new BusinessException(String.format("Guest named %s already exists", guest.getName()));
            }
        }
    }

    private Boolean isGuestAlreadyRegistered(Guest guest, List<Guest> guests) {
        return guests.stream().anyMatch(registeredGuest ->
                registeredGuest.getName().equals(guest.getName())
        );
    }
}
