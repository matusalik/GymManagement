package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.GroupClass.GroupClassCreateDTO;
import com.gymmanagement.gym_management_api.DTO.GroupClass.GroupClassDTO;
import com.gymmanagement.gym_management_api.DTO.GroupClass.NotDetailedGroupClassDTO;
import com.gymmanagement.gym_management_api.Entities.Client;
import com.gymmanagement.gym_management_api.Entities.GroupClass;
import com.gymmanagement.gym_management_api.Entities.Trainer;

import java.util.ArrayList;
import java.util.List;

public class GroupClassMapper {
    public static GroupClassDTO toDto(GroupClass groupClass){
        GroupClassDTO dto = new GroupClassDTO();
        dto.setName(groupClass.getName());
        dto.setDescription(groupClass.getDescription());
        dto.setTrainer(TrainerMapper.toDto(groupClass.getTrainer()));
        dto.setClients(ClientMapper.listToDto(groupClass.getClient_list()));
        dto.setDate_time(groupClass.getDate_time());
        dto.setMax_participants(groupClass.getMax_participants());
        return dto;
    }

    public static GroupClass toEntity(GroupClassCreateDTO dto, Trainer trainer, List<Client>clients){
        GroupClass groupClass = new GroupClass();
        groupClass.setName(dto.getName());
        groupClass.setDescription(dto.getDescription());
        groupClass.setTrainer(trainer);
        groupClass.setClient_list(clients);
        groupClass.setDate_time(dto.getDate_time());
        groupClass.setMax_participants(dto.getMax_participants());
        return groupClass;
    }

    public static Iterable<GroupClassDTO>listToDto(Iterable<GroupClass>groupClasses){
        List<GroupClassDTO>dtos = new ArrayList<>();
        for(GroupClass i : groupClasses){
            dtos.add(toDto(i));
        }
        return dtos;
    }

    public static NotDetailedGroupClassDTO toNotDetailedDto(GroupClass groupClass){
        NotDetailedGroupClassDTO dto = new NotDetailedGroupClassDTO();
        dto.setName(groupClass.getName());
        dto.setDescription(groupClass.getDescription());
        dto.setTrainer_name(groupClass.getTrainer().getFirst_name());
        dto.setTrainer_surname(groupClass.getTrainer().getLast_name());
        dto.setClients(ClientMapper.listToNotDetailedDto(groupClass.getClient_list()));
        dto.setDate_time(groupClass.getDate_time());
        dto.setMax_participants(groupClass.getMax_participants());
        return dto;
    }

    public static Iterable<NotDetailedGroupClassDTO>listToNotDetailedDto(Iterable<GroupClass>groupClasses){
        List<NotDetailedGroupClassDTO>dtos = new ArrayList<>();
        for(GroupClass i : groupClasses){
            dtos.add(toNotDetailedDto(i));
        }
        return dtos;
    }
}
