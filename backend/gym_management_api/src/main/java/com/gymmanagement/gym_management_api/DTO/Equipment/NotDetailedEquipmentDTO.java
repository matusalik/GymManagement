package com.gymmanagement.gym_management_api.DTO.Equipment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotDetailedEquipmentDTO {
    private Integer equipment_id;
    private String name;
    private String equipment_location;
}
