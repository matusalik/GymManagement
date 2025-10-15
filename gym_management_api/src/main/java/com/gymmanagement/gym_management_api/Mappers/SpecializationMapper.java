package com.gymmanagement.gym_management_api.Mappers;

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

    public static Iterable<SpecializationDTO> listToDto(Iterable<Specialization>specializations){
        List<SpecializationDTO>dtos = new ArrayList<>();
        for(Specialization i : specializations){
            dtos.add(toDto(i));
        }
        return dtos;
    }
}
