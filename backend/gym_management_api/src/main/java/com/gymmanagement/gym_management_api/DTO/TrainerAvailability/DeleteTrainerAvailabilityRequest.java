package com.gymmanagement.gym_management_api.DTO.TrainerAvailability;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteTrainerAvailabilityRequest {
    private Integer trainer_id;
    private String day_of_the_week;
}
