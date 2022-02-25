package com.tenniscourts.domain.guests.service;

import com.tenniscourts.domain.commons.validator.ArgumentValidator;
import com.tenniscourts.domain.guests.repository.GuestsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeleteGuestService {

    private final GuestsRepository guestsRepository;

    public void run(Long guestId){

        ArgumentValidator.isRequiredField(guestId, "Guest Id");
        ArgumentValidator.isEmptyValue(guestId, "Guest Id");

        guestsRepository.deleteById(guestId);
    }
}
