package com.gymmanagement.gym_management_api.DTO.TrainerAvailability;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;

@Getter
@Setter
public class TrainerAvailabilityCreateDTO {
    public Integer trainer_id;
    public String day_of_the_week;
    public LocalTime start_time;
    public LocalTime end_time;
}
