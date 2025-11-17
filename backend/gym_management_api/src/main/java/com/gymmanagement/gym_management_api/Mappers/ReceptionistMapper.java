package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.Receptionist.ReceptionistCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Receptionist.ReceptionistDTO;
import com.gymmanagement.gym_management_api.Entities.Receptionist;
import com.gymmanagement.gym_management_api.Enums.Role;
import com.gymmanagement.gym_management_api.Enums.UserType;
import com.gymmanagement.gym_management_api.Security.Password;

public class ReceptionistMapper {
    public static ReceptionistDTO toDto(Receptionist receptionist){
        ReceptionistDTO dto = new ReceptionistDTO();
        dto.setUsername(receptionist.getUsername());
        dto.setFirst_name(receptionist.getFirst_name());
        dto.setLast_name(receptionist.getLast_name());
        dto.setPhone(receptionist.getPhone());
        dto.setEmail(receptionist.getEmail());
        dto.setUser_type(UserType.RECEPTIONIST);
        return dto;
    }

    public static Receptionist toEntity(ReceptionistCreateDTO dto){
        Receptionist receptionist = new Receptionist();
        receptionist.setUsername(dto.getUsername());
        receptionist.setPassword(Password.ofRaw(dto.getPassword()));
        receptionist.setFirst_name(dto.getFirst_name());
        receptionist.setLast_name(dto.getLast_name());
        receptionist.setPhone(dto.getPhone());
        receptionist.setEmail(dto.getEmail());
        receptionist.setUser_type(UserType.RECEPTIONIST);
        receptionist.setRole(Role.ADMIN);
        return receptionist;
    }
}
