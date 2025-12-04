package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.User.NotDetailedUserDTO;
import com.gymmanagement.gym_management_api.DTO.User.UserCreateDTO;
import com.gymmanagement.gym_management_api.DTO.User.UserDTO;
import com.gymmanagement.gym_management_api.DTO.User.UserDetailedDTO;
import com.gymmanagement.gym_management_api.Entities.User;
import com.gymmanagement.gym_management_api.Security.Password;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setFirst_name(user.getFirst_name());
        dto.setLast_name(user.getLast_name());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        dto.setUser_type(user.getUser_type());
        return dto;
    }

    public static UserDetailedDTO toDetailedDTO(User user){
        UserDetailedDTO dto = new UserDetailedDTO();
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

    public static NotDetailedUserDTO toNotDetailedDto(User user){
        NotDetailedUserDTO dto = new NotDetailedUserDTO();
        dto.setUsername(user.getUsername());
        dto.setFirst_name(user.getFirst_name());
        dto.setLast_name(user.getLast_name());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static Iterable<UserDTO> listToDTO(Iterable<User>users){
        List<UserDTO> dtos = new ArrayList<>();
        for(User i : users){
            dtos.add(toDTO(i));
        }
        return dtos;
    }
}
