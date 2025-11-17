package com.gymmanagement.gym_management_api.Entities;
import com.gymmanagement.gym_management_api.Enums.Role;
import com.gymmanagement.gym_management_api.Enums.UserStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Receptionist extends User{
    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
