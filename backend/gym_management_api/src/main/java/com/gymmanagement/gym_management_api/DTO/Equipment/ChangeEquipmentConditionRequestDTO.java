package com.gymmanagement.gym_management_api.DTO.Equipment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeEquipmentConditionRequestDTO {
    private Integer equipment_id;
    private String condition;
}
