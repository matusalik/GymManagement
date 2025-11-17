package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.TrainingPlan.TrainingPlanCreateDTO;
import com.gymmanagement.gym_management_api.DTO.TrainingPlan.TrainingPlanDTO;
import com.gymmanagement.gym_management_api.Entities.Exercise;
import com.gymmanagement.gym_management_api.Entities.TrainingGoal;
import com.gymmanagement.gym_management_api.Entities.TrainingPlan;
import com.gymmanagement.gym_management_api.Enums.TrainingDifficultyLevel;

import java.util.ArrayList;
import java.util.List;

public class TrainingPlanMapper {
    public static TrainingPlanDTO toDto(TrainingPlan trainingPlan){
        TrainingPlanDTO dto = new TrainingPlanDTO();
        dto.setName(trainingPlan.getName());
        dto.setDescription(trainingPlan.getDescription());
        dto.setDifficulty_level(trainingPlan.getDifficulty_level().name());
        dto.setTraining_goal(TrainingGoalMapper.toDto(trainingPlan.getTraining_goal()));
        dto.setExercises(ExerciseMapper.listToDto(trainingPlan.getExercises()));
        return dto;
    }

    public static TrainingPlan toEntity(TrainingPlanCreateDTO dto, TrainingGoal tg, List<Exercise>exercises){
        TrainingPlan trainingPlan = new TrainingPlan();
        trainingPlan.setName(dto.getName());
        trainingPlan.setDescription(dto.getDescription());
        trainingPlan.setDifficulty_level(TrainingDifficultyLevel.valueOf(dto.getDifficulty_level().toUpperCase()));
        trainingPlan.setTraining_goal(tg);
        trainingPlan.setExercises(exercises);
        return trainingPlan;
    }

    public static Iterable<TrainingPlanDTO>listToDto(Iterable<TrainingPlan>tps){
        List<TrainingPlanDTO> dtos = new ArrayList<>();
        for(TrainingPlan i : tps){
            dtos.add(toDto(i));
        }
        return dtos;
    }
}
