package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.Client.ClientCreateDTO;
import com.gymmanagement.gym_management_api.Entities.Client;
import com.gymmanagement.gym_management_api.DTO.Client.ClientDTO;
import com.gymmanagement.gym_management_api.Enums.UserType;
import com.gymmanagement.gym_management_api.Security.Password;

import java.util.ArrayList;
import java.util.List;

public class ClientMapper {
    public static ClientDTO toDto(Client client){
        ClientDTO dto = new ClientDTO();
        dto.setUsername(client.getUsername());
        dto.setFirst_name(client.getFirst_name());
        dto.setLast_name(client.getLast_name());
        dto.setPhone(client.getPhone());
        dto.setEmail(client.getEmail());
        dto.setUser_type(UserType.CLIENT);
        dto.setDate_of_birth(client.getDate_of_birth());
        dto.setAddress(client.getAddress());
        dto.setRegistration_date(client.getRegistration_date());
        dto.setStatus(client.getStatus());
        dto.setMembership_id(client.getMembership().getMembershipId());
        dto.setTraining_goal_id(client.getTraining_goal().getTrainingGoalId());
        return dto;
    }

    public static Client toEntityWithoutRelations(ClientCreateDTO dto){
        Client client = new Client();
        client.setUsername(dto.getUsername());
        client.setPassword(Password.ofRaw(dto.getPassword()));
        client.setFirst_name(dto.getFirst_name());
        client.setLast_name(dto.getLast_name());
        client.setPhone(dto.getPhone());
        client.setEmail(dto.getEmail());
        client.setUser_type(UserType.CLIENT);
        client.setDate_of_birth(dto.getDate_of_birth());
        client.setAddress(dto.getAddress());
        client.setRegistration_date(dto.getRegistration_date());
        client.setStatus(dto.getStatus());
        return client;
    }

    public static Iterable<ClientDTO> listToDto(Iterable<Client>clients){
        List<ClientDTO> dtos = new ArrayList<>();
        for(Client i : clients){
            dtos.add(toDto(i));
        }
        return dtos;
    }
}
