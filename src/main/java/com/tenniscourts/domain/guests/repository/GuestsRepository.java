package com.tenniscourts.domain.guests.repository;

import com.tenniscourts.domain.guests.model.Guest;

import java.util.List;
import java.util.Optional;

public interface GuestsRepository {

    Optional<Guest> findById(Long guestId);
    Optional<List<Guest>> findAll();
    Optional<List<Guest>> findByName(String name);
    Optional<Guest> save(Guest guest);
    void deleteById(Long guestId);
}
