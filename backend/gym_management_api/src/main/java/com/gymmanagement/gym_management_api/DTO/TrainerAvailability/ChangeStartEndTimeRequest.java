package com.gymmanagement.gym_management_api.DTO.TrainerAvailability;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class ChangeStartEndTimeRequest {
    public Integer trainer_id;
    public String day_of_the_week;
    @Schema(type = "string", example = "09:00:00")
    public LocalTime start_time;
    @Schema(type = "string", example = "09:00:00")
    public LocalTime end_time;
}
