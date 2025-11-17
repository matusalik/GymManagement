package com.gymmanagement.gym_management_api.DTO.GroupClass;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GroupClassCreateDTO {
    public String name;
    public String description;
    public Integer trainer_id;
    public Iterable<Integer>clients_ids;
    public LocalDateTime date_time;
    public Integer max_participants;
}
