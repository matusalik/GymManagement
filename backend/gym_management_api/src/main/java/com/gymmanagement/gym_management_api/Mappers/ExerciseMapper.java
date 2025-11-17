package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.Equipment.EquipmentDTO;
import com.gymmanagement.gym_management_api.DTO.Exercise.ExerciseCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Exercise.ExerciseDTO;
import com.gymmanagement.gym_management_api.Entities.Equipment;
import com.gymmanagement.gym_management_api.Entities.Exercise;
import com.gymmanagement.gym_management_api.Enums.ExerciseCategory;

import java.util.ArrayList;
import java.util.List;

public class ExerciseMapper {
    public static ExerciseDTO toDto(Exercise exercise){
        ExerciseDTO dto = new ExerciseDTO();
        dto.setName(exercise.getName());
        dto.setDescription(exercise.getDescription());
        dto.setExercise_category(exercise.getExercise_category().name());
        dto.setEquipment(EquipmentMapper.toDto(exercise.getEquipment()));
        dto.setInstructions(exercise.getInstructions());
        return dto;
    }

    public static Exercise toEntity(ExerciseCreateDTO dto, Equipment eq){
        Exercise exercise = new Exercise();
        exercise.setName(dto.getName());
        exercise.setDescription(dto.getDescription());
        exercise.setExercise_category(ExerciseCategory.valueOf(dto.getExercise_category().toUpperCase()));
        exercise.setEquipment(eq);
        exercise.setInstructions(dto.getInstructions());
        return exercise;
    }



    public static Iterable<ExerciseDTO>listToDto(Iterable<Exercise>exercises){
        List<ExerciseDTO> dtos = new ArrayList<>();
        for(Exercise i : exercises){
            dtos.add(toDto(i));
        }
        return dtos;
    }
}
