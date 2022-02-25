package com.tenniscourts.domain.guests.service;

import com.tenniscourts.domain.commons.validator.ArgumentValidator;
import com.tenniscourts.exceptions.BusinessException;
import com.tenniscourts.domain.guests.model.Guest;
import com.tenniscourts.domain.guests.repository.GuestsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FindGuestService {

    @Autowired
    private GuestsRepository guestsRepository;

    public List<Guest> findAll() {

        return guestsRepository
                .findAll()
                .orElse(Collections.emptyList());
    }

    public Guest findById(Long guestId) {
        ArgumentValidator.isRequiredField(guestId, "Guest Id");
        ArgumentValidator.isEmptyValue(guestId, "Guest Id");

        return guestsRepository
                .findById(guestId)
                .orElseThrow(
                        () -> new BusinessException(String.format("The user with Id %s does not exist", guestId))
                );
    }

    public List<Guest> findByName(String name) {
        return guestsRepository
                .findByName(Objects.isNull(name) ? "" : name.toUpperCase())
                .orElse(Collections.emptyList());
    }

}
