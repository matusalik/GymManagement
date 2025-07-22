package com.gymmanagement.gym_management_api.DTO.Trainer;

import com.gymmanagement.gym_management_api.DTO.User.UserDTO;
import com.gymmanagement.gym_management_api.Enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainerDTO extends UserDTO {
    public String bio;
    public UserStatus status;
}