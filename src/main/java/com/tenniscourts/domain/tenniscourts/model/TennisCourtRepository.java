package com.tenniscourts.domain.tenniscourts.model;

import java.util.Optional;

public interface TennisCourtRepository{
    Optional<TennisCourt> findById(Long Id);
    Optional<TennisCourt> findByName(String name);
    Optional<TennisCourt> save(TennisCourt tennisCourt);

}
