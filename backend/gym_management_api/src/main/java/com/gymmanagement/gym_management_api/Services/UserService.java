package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.Security.ChangePasswordRequestDTO;
import com.gymmanagement.gym_management_api.DTO.User.NotDetailedUserDTO;
import com.gymmanagement.gym_management_api.DTO.User.UserDetailedDTO;
import com.gymmanagement.gym_management_api.Security.Password;
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

    public NotDetailedUserDTO getNotDetailedUserById(Integer id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " not found."));
        return UserMapper.toNotDetailedDto(user);
    }

    public Iterable<UserDTO>getUsers(){
        return UserMapper.listToDTO(userRepository.findAll());
    }

    //---PATCH---//

    public void changeUserPassword(Integer id, ChangePasswordRequestDTO dto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " not found."));
        Password newPassword = Password.ofRaw(dto.getNewPassword());
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    //----PUT----//

    public void updateUser(Integer id, NotDetailedUserDTO dto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " not found."));
        user.setUsername(dto.getUsername());
        user.setFirst_name(dto.getFirst_name());
        user.setLast_name(dto.getLast_name());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        userRepository.save(user);
    }
}
