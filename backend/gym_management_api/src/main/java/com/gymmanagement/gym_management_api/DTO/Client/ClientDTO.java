package com.gymmanagement.gym_management_api.DTO.Client;

import com.gymmanagement.gym_management_api.DTO.User.UserDTO;
import com.gymmanagement.gym_management_api.Enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO extends UserDTO {
    private String address;
    private UserStatus status;
}
