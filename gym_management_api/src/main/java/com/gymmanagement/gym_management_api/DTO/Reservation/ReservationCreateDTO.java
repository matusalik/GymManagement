package com.gymmanagement.gym_management_api.DTO.Reservation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationCreateDTO {
    public Integer client_id;
    public Integer equipment_id;
    public Integer group_class_id;
    public Integer trainer_id;
    public LocalDateTime date_time;
    public String reservation_status;
}
