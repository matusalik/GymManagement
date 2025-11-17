package com.gymmanagement.gym_management_api.DTO.Exercise;

import com.gymmanagement.gym_management_api.DTO.Equipment.EquipmentDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseCreateDTO {
    public String name;
    public String description;
    public String exercise_category;
    public Integer equipmentId;
    public String instructions;
}
