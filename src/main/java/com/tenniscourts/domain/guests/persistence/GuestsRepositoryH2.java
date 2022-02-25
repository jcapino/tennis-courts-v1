package com.tenniscourts.domain.guests.persistence;

import com.tenniscourts.domain.guests.model.Guest;
import com.tenniscourts.domain.guests.persistence.dao.GuestsDao;
import com.tenniscourts.domain.guests.repository.GuestsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class GuestsRepositoryH2 implements GuestsRepository {

    private final GuestsDao guestsDao;

    @Override
    public Optional<Guest> findById(Long idGuest) {
        return guestsDao.findById(idGuest);
    }

    @Override
    public Optional<List<Guest>> findAll() {
        return Optional.of(guestsDao.findAll());
    }

    @Override
    public Optional<List<Guest>> findByName(String name) {
        return Optional.ofNullable(guestsDao.findByName(name));
    }

    @Override
    public Optional<Guest> save(Guest guest) {
        return Optional.ofNullable(guestsDao.save(guest));
    }

    @Override
    public void deleteById(Long guestId) {
        guestsDao.deleteById(guestId);
    }
}
