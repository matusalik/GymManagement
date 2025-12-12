package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.Specialization.NotDetailedSpecializationDTO;
import com.gymmanagement.gym_management_api.DTO.Specialization.SpecializationDTO;
import com.gymmanagement.gym_management_api.Entities.Specialization;

import java.util.ArrayList;
import java.util.List;

public class SpecializationMapper {
    public static SpecializationDTO toDto(Specialization specialization){
        SpecializationDTO dto = new SpecializationDTO();
        dto.setName(specialization.getName());
        dto.setDescription(specialization.getDescription());
        return dto;
    }

    public static Specialization toEntity(SpecializationDTO dto){
        Specialization specialization = new Specialization();
        specialization.setName(dto.getName());
        specialization.setDescription(dto.getDescription());
        return specialization;
    }

    public static NotDetailedSpecializationDTO toNotDetailedDto(Specialization specialization){
        NotDetailedSpecializationDTO dto = new NotDetailedSpecializationDTO();
        dto.setId(specialization.getSpecializationId());
        dto.setName(specialization.getName());
        return dto;
    }

    public static Iterable<SpecializationDTO> listToDto(Iterable<Specialization>specializations){
        List<SpecializationDTO>dtos = new ArrayList<>();
        for(Specialization i : specializations){
            dtos.add(toDto(i));
        }
        return dtos;
    }

    public static Iterable<NotDetailedSpecializationDTO> listToNotDetailedDto(Iterable<Specialization>specializations){
        List<NotDetailedSpecializationDTO>dtos = new ArrayList<>();
        for(Specialization i : specializations){
            dtos.add(toNotDetailedDto(i));
        }
        return dtos;
    }
}
