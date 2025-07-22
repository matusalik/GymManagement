package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.User.UserCreateDTO;
import com.gymmanagement.gym_management_api.DTO.User.UserDTO;
import com.gymmanagement.gym_management_api.Entities.User;
import com.gymmanagement.gym_management_api.Security.Password;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserCreateDTO toDetailedDTO(User user){
        UserCreateDTO dto = new UserCreateDTO();
        dto.setUser_id(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPasswordHash());
        dto.setFirst_name(user.getFirst_name());
        dto.setLast_name(user.getLast_name());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        dto.setUser_type(user.getUser_type());
        return dto;
    }

    public static Iterable<UserCreateDTO> listToDTO(Iterable<User>users){
        List<UserCreateDTO> dtos = new ArrayList<>();
        for(User i : users){
            dtos.add(toDetailedDTO(i));
        }
        return dtos;
    }
}
