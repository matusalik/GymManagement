package com.gymmanagement.gym_management_api.Entities;
import com.gymmanagement.gym_management_api.Enums.UserStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Receptionist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer receptionistId;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    UserStatus status;
}
