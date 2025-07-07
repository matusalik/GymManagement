package com.gymmanagement.gym_management_api.Entities;
import java.time.LocalTime;

import com.gymmanagement.gym_management_api.Enums.DayOfTheWeek;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    Integer trainerAvailabilityId;

    Trainer trainer;

    DayOfTheWeek day_of_the_week;

    LocalTime start_time;

    LocalTime end_time;
}
