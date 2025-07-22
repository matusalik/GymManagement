package com.gymmanagement.gym_management_api.DTO.Trainer;

import com.gymmanagement.gym_management_api.DTO.User.UserCreateDTO;
import com.gymmanagement.gym_management_api.Enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainerCreateDTO extends UserCreateDTO {
    private String bio;
    private UserStatus status;
}
