package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.User.UserCreateDTO;
import com.gymmanagement.gym_management_api.DTO.User.UserDetailedDTO;
import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management_api.DTO.User.UserDTO;
import com.gymmanagement.gym_management_api.Entities.User;
import com.gymmanagement.gym_management_api.Mappers.UserMapper;
import com.gymmanagement.gym_management_api.Repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //-----GET-----//

    public UserDetailedDTO getUserById(Integer id){
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " not found."));
        return UserMapper.toDetailedDTO(user);
    }

    public Iterable<UserDTO>getUsers(){
        return UserMapper.listToDTO(userRepository.findAll());
    }
}
