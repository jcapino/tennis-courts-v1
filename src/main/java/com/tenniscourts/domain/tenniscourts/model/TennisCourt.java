package com.tenniscourts.domain.tenniscourts.model;

import com.tenniscourts.config.persistence.BaseEntity;
import com.tenniscourts.domain.schedules.model.Schedule;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "tennisCourtSchedules")
@ToString
public class TennisCourt extends BaseEntity<Long> {

    @Column
    @NotNull
    private String name;

    @Transient
    private List<Schedule> tennisCourtSchedules;
}
