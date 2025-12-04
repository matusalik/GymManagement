package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.Trainer.NotDetailedTrainerDTO;
import com.gymmanagement.gym_management_api.DTO.TrainingGoal.NotDetailedTrainingGoalDTO;
import com.gymmanagement.gym_management_api.DTO.TrainingGoal.TrainingGoalCreateDTO;
import com.gymmanagement.gym_management_api.DTO.TrainingGoal.TrainingGoalDTO;
import com.gymmanagement.gym_management_api.DTO.User.NotDetailedUserDTO;
import com.gymmanagement.gym_management_api.Entities.TrainingGoal;

import java.util.ArrayList;
import java.util.List;

public class TrainingGoalMapper {
    public static TrainingGoalDTO toDto(TrainingGoal tg){
        TrainingGoalDTO dto = new TrainingGoalDTO();
        dto.setTraining_goal_id(tg.getTrainingGoalId());
        dto.setName(tg.getName());
        dto.setDescription(tg.getDescription());
        return dto;
    }

    public static TrainingGoal toEntity(TrainingGoalCreateDTO dto){
        TrainingGoal tg = new TrainingGoal();
        tg.setName(dto.getName());
        tg.setDescription(dto.getDescription());
        return tg;
    }

    public static NotDetailedTrainingGoalDTO toNotDetailedDto(TrainingGoal tg){
        NotDetailedTrainingGoalDTO dto = new NotDetailedTrainingGoalDTO();
        dto.setId(tg.getTrainingGoalId());
        dto.setName(tg.getName());
        return dto;
    }

    public static Iterable<TrainingGoalDTO> listToDto(Iterable<TrainingGoal>tgs){
        List<TrainingGoalDTO> dtos = new ArrayList<>();
        for(TrainingGoal i : tgs){
            dtos.add(toDto(i));
        }
        return dtos;
    }

    public static Iterable<NotDetailedTrainingGoalDTO> listToNotDetailedDto(Iterable<TrainingGoal>tgs){
        List<NotDetailedTrainingGoalDTO> dtos = new ArrayList<>();
        for(TrainingGoal i : tgs){
            dtos.add(toNotDetailedDto(i));
        }
        return dtos;
    }
}
