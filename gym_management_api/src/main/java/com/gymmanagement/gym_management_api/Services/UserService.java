package com.gymmanagement.gym_management_api.Services;

import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management_api.DTO.UserDTO;
import com.gymmanagement.gym_management_api.Entities.User;
import com.gymmanagement.gym_management_api.Mappers.UserMapper;
import com.gymmanagement.gym_management_api.Repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO getUserById(Integer id){
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " not found."));
        return UserMapper.toDTO(user);
    }
}
