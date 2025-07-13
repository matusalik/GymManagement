package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.User.UserCreateDTO;
import com.gymmanagement.gym_management_api.DTO.User.UserDTO;
import com.gymmanagement.gym_management_api.Entities.User;
import com.gymmanagement.gym_management_api.Security.Password;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static UserCreateDTO toDTO(User user){
        UserCreateDTO dto = new UserCreateDTO();
        dto.setUsername(user.getUsername());
        dto.setFirst_name(user.getFirst_name());
        dto.setLast_name(user.getLast_name());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        dto.setUser_type(user.getUser_type());
        return dto;
    }

    public static UserDTO toDetailedDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setUser_id(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setFirst_name(user.getFirst_name());
        dto.setLast_name(user.getLast_name());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        dto.setUser_type(user.getUser_type());
        return dto;
    }

    public static User toEntity(UserDTO dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFirst_name(dto.getFirst_name());
        user.setLast_name(dto.getLast_name());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setUser_type(dto.getUser_type());
        return user;
    }

    public static User toEntity(UserCreateDTO dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(Password.ofRaw(dto.getPassword()));
        user.setFirst_name(dto.getFirst_name());
        user.setLast_name(dto.getLast_name());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setUser_type(dto.getUser_type());
        return user;
    }

    public static Iterable<UserDTO> listToDTO(Iterable<User>users){
        List<UserDTO> dtos = new ArrayList<>();
        for(User i : users){
            dtos.add(toDetailedDTO(i));
        }
        return dtos;
    }
}
