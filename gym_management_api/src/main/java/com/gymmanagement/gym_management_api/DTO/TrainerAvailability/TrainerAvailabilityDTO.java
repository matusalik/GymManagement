package com.gymmanagement.gym_management_api.DTO.TrainerAvailability;

import com.gymmanagement.gym_management_api.DTO.Trainer.TrainerDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class TrainerAvailabilityDTO {
    public TrainerDTO trainer;
    public String day_of_the_week;
    public LocalTime start_time;
    public LocalTime end_time;
}
