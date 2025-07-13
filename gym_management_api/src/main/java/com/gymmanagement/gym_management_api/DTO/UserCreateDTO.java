package com.gymmanagement.gym_management_api.DTO;

import com.gymmanagement.gym_management_api.Enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDTO {
    private String username;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    private UserType user_type;
}
