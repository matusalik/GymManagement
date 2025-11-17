package com.gymmanagement.gym_management_api.DTO.Client;

import com.gymmanagement.gym_management_api.DTO.User.UserCreateDTO;
import com.gymmanagement.gym_management_api.Enums.UserStatus;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ClientCreateDTO extends UserCreateDTO {
    private LocalDate date_of_birth;
    private String address;
    private LocalDate registration_date;
    private UserStatus status;
    private Integer membership_id;
    private Integer training_goal_id;
}