package com.tenniscourts.domain.tenniscourts.service;

import com.tenniscourts.domain.commons.validator.ArgumentValidator;
import com.tenniscourts.exceptions.BusinessException;
import com.tenniscourts.domain.tenniscourts.model.TennisCourt;
import com.tenniscourts.domain.tenniscourts.model.TennisCourtRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateTennisCourtService {

    private final TennisCourtRepository tennisCourtRepository;

    public TennisCourt run(TennisCourt tennisCourt) {

        validateTennisCourtBeforeSave(tennisCourt);

        return tennisCourtRepository
                .save(tennisCourt)
                .orElseThrow(() -> new BusinessException("Error: Failed to create record in database"));
    }

    private void validateTennisCourtBeforeSave(TennisCourt tennisCourt) {

        ArgumentValidator.isRequiredField(tennisCourt.getName(), "Name");
        ArgumentValidator.isEmptyValue(tennisCourt.getName(), "Name");

        Optional<TennisCourt> tennisCourtExists = tennisCourtRepository.findByName(tennisCourt.getName());
        if (tennisCourtExists.isPresent()) {
            throw new BusinessException("Tennis Courts already exists");
        }
    }
}
