package com.gymmanagement.gym_management_api.DTO.Equipment;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class EquipmentDTO {
    private String name;
    private String equipment_type;
    private String equipment_location;
    private String equipment_condition;
    private String equipment_availability;
}
