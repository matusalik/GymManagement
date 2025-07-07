package com.gymmanagement.gym_management_api.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Integer userId;
    private String username;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    private String user_type;
}
