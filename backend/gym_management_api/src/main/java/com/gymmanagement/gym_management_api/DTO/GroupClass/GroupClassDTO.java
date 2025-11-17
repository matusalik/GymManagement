package com.gymmanagement.gym_management_api.DTO.GroupClass;

import com.gymmanagement.gym_management_api.DTO.Client.ClientDTO;
import com.gymmanagement.gym_management_api.DTO.Trainer.TrainerDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class GroupClassDTO {
    public String name;
    public String description;
    public TrainerDTO trainer;
    public Iterable<ClientDTO>clients;
    public LocalDateTime date_time;
    public Integer max_participants;
}
