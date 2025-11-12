package com.gymmanagement.gym_management_api.DTO.Receptionist;

import com.gymmanagement.gym_management_api.DTO.User.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceptionistDTO extends UserDTO {
    private String status;
}
