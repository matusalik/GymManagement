package com.gymmanagement.gym_management_api.DTO.GroupClass;

import com.gymmanagement.gym_management_api.DTO.Client.NotDetailedClientDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotDetailedGroupClassDTO {
    public String name;
    public String description;
    public String trainer_name;
    public String trainer_surname;
    public Iterable<NotDetailedClientDTO>clients;
    public LocalDateTime date_time;
    public Integer max_participants;
}
