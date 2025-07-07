package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.UserDTO;
import com.gymmanagement.gym_management_api.Entities.User;

public class UserMapper {
    public static UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setFirst_name(user.getFirst_name());
        dto.setLast_name(user.getLast_name());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        dto.setUser_type(user.getUser_type().name());
        return dto;
    }
}
