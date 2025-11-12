package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.Trainer.TrainerCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Trainer.TrainerDTO;
import com.gymmanagement.gym_management_api.Entities.Trainer;
import com.gymmanagement.gym_management_api.Enums.Role;
import com.gymmanagement.gym_management_api.Enums.UserType;
import com.gymmanagement.gym_management_api.Security.Password;

import java.util.ArrayList;
import java.util.List;

public class TrainerMapper {
    public static TrainerDTO toDto(Trainer trainer){
        TrainerDTO dto = new TrainerDTO();
        dto.setUsername(trainer.getUsername());
        dto.setFirst_name(trainer.getFirst_name());
        dto.setLast_name(trainer.getLast_name());
        dto.setPhone(trainer.getPhone());
        dto.setEmail(trainer.getEmail());
        dto.setUser_type(UserType.TRAINER);
        dto.setBio(trainer.getBio());
        dto.setStatus(trainer.getStatus());
        return dto;
    }

    public static Trainer toEntityWithoutRelations(TrainerCreateDTO dto){
        Trainer trainer = new Trainer();
        trainer.setUsername(dto.getUsername());
        trainer.setPassword(Password.ofRaw(dto.getPassword()));
        trainer.setFirst_name(dto.getFirst_name());
        trainer.setLast_name(dto.getLast_name());
        trainer.setPhone(dto.getPhone());
        trainer.setEmail(dto.getEmail());
        trainer.setUser_type(UserType.TRAINER);
        trainer.setRole(Role.TRAINER);
        trainer.setBio(dto.getBio());
        trainer.setStatus(dto.getStatus());
        return trainer;
    }

    public static Iterable<TrainerDTO>listToDto(Iterable<Trainer>trainers){
        List<TrainerDTO> dtos = new ArrayList<>();
        for(Trainer i : trainers){
            dtos.add(toDto(i));
        }
        return dtos;
    }
}
