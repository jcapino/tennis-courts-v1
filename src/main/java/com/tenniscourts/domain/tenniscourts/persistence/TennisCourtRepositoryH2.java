package com.tenniscourts.domain.tenniscourts.persistence;

import com.tenniscourts.domain.tenniscourts.model.TennisCourt;
import com.tenniscourts.domain.tenniscourts.model.TennisCourtRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class TennisCourtRepositoryH2 implements TennisCourtRepository {

    private final TennisCourtDao tennisCourtDao;

    @Override
    public Optional<TennisCourt> save(TennisCourt tennisCourt) {
        return Optional.of(tennisCourtDao.save(tennisCourt));
    }

    @Override
    public Optional<TennisCourt> findById(Long id) {
        return tennisCourtDao.findById(id);
    }

    @Override
    public Optional<TennisCourt> findByName(String name) {
        return Optional.of(tennisCourtDao.findByName(name));
    }
}
