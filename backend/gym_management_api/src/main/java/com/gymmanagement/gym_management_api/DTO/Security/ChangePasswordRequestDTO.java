package com.gymmanagement.gym_management_api.DTO.Security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequestDTO {
    private String username;
    private String oldPassword;
    private String newPassword;
    private String newPasswordRepeat;
}
