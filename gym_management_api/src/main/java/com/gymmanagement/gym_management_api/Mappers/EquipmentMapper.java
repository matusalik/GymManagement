package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.Equipment.EquipmentDTO;
import com.gymmanagement.gym_management_api.Entities.Equipment;
import com.gymmanagement.gym_management_api.Enums.EquipmentAvailability;
import com.gymmanagement.gym_management_api.Enums.EquipmentCondition;
import com.gymmanagement.gym_management_api.Enums.EquipmentLocation;
import com.gymmanagement.gym_management_api.Enums.EquipmentType;
import jdk.jshell.Snippet;

import java.util.ArrayList;
import java.util.List;

public class EquipmentMapper {
    public static EquipmentDTO toDto(Equipment equipment){
        EquipmentDTO dto = new EquipmentDTO();
        dto.setName(equipment.getName());
        dto.setEquipment_type(equipment.getEquipment_type().name());
        dto.setEquipment_location(equipment.getEquipment_location().name());
        dto.setEquipment_condition(equipment.getEquipment_condition().name());
        dto.setEquipment_availability(equipment.getEquipment_availability().name());
        return dto;
    }

    public static Equipment toEntity(EquipmentDTO dto){
        Equipment eq = new Equipment();
        eq.setName(dto.getName());
        eq.setEquipment_type(EquipmentType.valueOf(dto.getEquipment_type().toUpperCase()));
        eq.setEquipment_location(EquipmentLocation.valueOf(dto.getEquipment_location().toUpperCase()));
        eq.setEquipment_condition(EquipmentCondition.valueOf(dto.getEquipment_condition().toUpperCase()));
        eq.setEquipment_availability(EquipmentAvailability.valueOf(dto.getEquipment_availability().toUpperCase()));
        return eq;
    }

    public static Iterable<EquipmentDTO>listToDto(Iterable<Equipment>equipment){
        List<EquipmentDTO> dtos = new ArrayList<>();
        for(Equipment i : equipment){
            dtos.add(toDto(i));
        }
        return dtos;
    }
}
