package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.TrainerAvailability.NotDetailedTrainerAvailabilityDTO;
import com.gymmanagement.gym_management_api.DTO.TrainerAvailability.TrainerAvailabilityCreateDTO;
import com.gymmanagement.gym_management_api.DTO.TrainerAvailability.TrainerAvailabilityDTO;
import com.gymmanagement.gym_management_api.Entities.Trainer;
import com.gymmanagement.gym_management_api.Entities.TrainerAvailability;
import com.gymmanagement.gym_management_api.Enums.DayOfTheWeek;

import java.util.ArrayList;
import java.util.List;

public class TrainerAvailabilityMapper {
    public static TrainerAvailabilityDTO toDto(TrainerAvailability trainerAvailability){
        TrainerAvailabilityDTO dto = new TrainerAvailabilityDTO();
        dto.setTrainer(TrainerMapper.toDto(trainerAvailability.getTrainer()));
        dto.setDay_of_the_week(trainerAvailability.getDay_of_the_week().name());
        dto.setStart_time(trainerAvailability.getStart_time());
        dto.setEnd_time(trainerAvailability.getEnd_time());
        return dto;
    }

    public static TrainerAvailability toEntity(TrainerAvailabilityCreateDTO dto, Trainer trainer){
        TrainerAvailability trainerAvailability = new TrainerAvailability();
        trainerAvailability.setTrainer(trainer);
        trainerAvailability.setDay_of_the_week(DayOfTheWeek.valueOf(dto.getDay_of_the_week().toUpperCase()));
        trainerAvailability.setStart_time(dto.getStart_time());
        trainerAvailability.setEnd_time(dto.getEnd_time());
        return trainerAvailability;
    }

    public static NotDetailedTrainerAvailabilityDTO toNotDetailedDto(TrainerAvailability trainerAvailability){
        NotDetailedTrainerAvailabilityDTO dto = new NotDetailedTrainerAvailabilityDTO();
        dto.setTrainer_id(trainerAvailability.getTrainer().getUserId());
        dto.setDay_of_the_week(trainerAvailability.getDay_of_the_week().name());
        dto.setStart_time(trainerAvailability.getStart_time());
        dto.setEnd_time(trainerAvailability.getEnd_time());
        return dto;
    }

    public static Iterable<TrainerAvailabilityDTO>listToDto(Iterable<TrainerAvailability>trainerAvailabilities){
        List<TrainerAvailabilityDTO>dtos = new ArrayList<>();
        for(TrainerAvailability i : trainerAvailabilities){
            dtos.add(toDto(i));
        }
        return dtos;
    }

    public static Iterable<NotDetailedTrainerAvailabilityDTO>listToNotDetailedDto(Iterable<TrainerAvailability>trainerAvailabilities){
        List<NotDetailedTrainerAvailabilityDTO>dtos = new ArrayList<>();
        for(TrainerAvailability i : trainerAvailabilities){
            dtos.add(toNotDetailedDto(i));
        }
        return dtos;
    }
}
