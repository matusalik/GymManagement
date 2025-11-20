package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.ClientAttendance.ClientAttendanceDTO;
import com.gymmanagement.gym_management_api.Entities.Client;
import com.gymmanagement.gym_management_api.Entities.ClientAttendance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClientAttendanceMapper {
    public static ClientAttendanceDTO toDto(ClientAttendance clientAttendance){
        ClientAttendanceDTO dto = new ClientAttendanceDTO();
        dto.setClient(ClientMapper.toNotDetailedClientDto(clientAttendance.getClient()));
        dto.setDate_time(clientAttendance.getDate_time());
        return dto;
    }

    public static ClientAttendance toEntity(Client client, LocalDateTime dateTime){
        ClientAttendance clientAttendance = new ClientAttendance();
        clientAttendance.setClient(client);
        clientAttendance.setDate_time(dateTime);
        return clientAttendance;
    }

    public static Iterable<ClientAttendanceDTO>listToDto(Iterable<ClientAttendance>clientAttendances){
        List<ClientAttendanceDTO> dtos = new ArrayList<>();
        for(ClientAttendance i : clientAttendances){
            dtos.add(toDto(i));
        }
        return dtos;
    }
}
