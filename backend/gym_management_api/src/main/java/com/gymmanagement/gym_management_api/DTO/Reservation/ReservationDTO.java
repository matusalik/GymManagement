package com.gymmanagement.gym_management_api.DTO.Reservation;

import com.gymmanagement.gym_management_api.DTO.Client.ClientDTO;
import com.gymmanagement.gym_management_api.DTO.Equipment.EquipmentDTO;
import com.gymmanagement.gym_management_api.DTO.GroupClass.GroupClassDTO;
import com.gymmanagement.gym_management_api.DTO.Trainer.TrainerDTO;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationDTO {
    public ClientDTO client;
    public EquipmentDTO equipment;
    public GroupClassDTO group_class;
    public TrainerDTO trainer;
    public LocalDateTime date_time;
    public String reservation_status;
}
