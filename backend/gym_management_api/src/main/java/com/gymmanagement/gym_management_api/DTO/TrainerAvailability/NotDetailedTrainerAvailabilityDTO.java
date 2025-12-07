package com.gymmanagement.gym_management_api.DTO.TrainerAvailability;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class NotDetailedTrainerAvailabilityDTO {
    private Integer trainer_id;
    private String day_of_the_week;
    private LocalTime start_time;
    private LocalTime end_time;
}
