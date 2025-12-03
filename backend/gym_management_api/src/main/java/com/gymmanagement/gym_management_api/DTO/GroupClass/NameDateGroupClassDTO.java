package com.gymmanagement.gym_management_api.DTO.GroupClass;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NameDateGroupClassDTO {
    public Integer group_class_id;
    public String name;
    public LocalDateTime date_time;
}
