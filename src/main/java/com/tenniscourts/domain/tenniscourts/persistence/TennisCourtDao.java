package com.tenniscourts.domain.tenniscourts.persistence;

import com.tenniscourts.domain.tenniscourts.model.TennisCourt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TennisCourtDao extends JpaRepository<TennisCourt, Long> {

    @Query("SELECT tnc " +
            "FROM TennisCourt as tnc " +
            "WHERE tnc.name like :name")
    TennisCourt findByName(@Param("name") String name);
}
