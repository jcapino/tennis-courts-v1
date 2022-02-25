package com.tenniscourts.domain.guests.persistence.dao;

import com.tenniscourts.domain.guests.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GuestsDao extends JpaRepository<Guest, Long> {

    @Query("SELECT GUE " +
            "FROM Guest GUE " +
            "WHERE UPPER(GUE.name) LIKE CONCAT('%',:name, '%')")
    List<Guest> findByName(@Param("name") String name);
}
