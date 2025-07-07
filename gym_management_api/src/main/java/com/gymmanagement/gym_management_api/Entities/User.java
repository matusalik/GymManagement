package com.gymmanagement.gym_management_api.Entities;
import com.gymmanagement.gym_management_api.Enums.UserType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;

    String username;

    String password;

    String first_name;

    String last_name;

    String phone;

    String email;

    @Enumerated(EnumType.STRING)
    UserType user_type;
}
