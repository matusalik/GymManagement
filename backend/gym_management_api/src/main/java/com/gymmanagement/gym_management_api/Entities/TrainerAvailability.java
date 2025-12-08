package com.gymmanagement.gym_management_api.Entities;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gymmanagement.gym_management_api.Enums.DayOfTheWeek;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class TrainerAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainerAvailabilityId;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @Enumerated(EnumType.STRING)
    private DayOfTheWeek day_of_the_week;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime start_time;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime end_time;
}
