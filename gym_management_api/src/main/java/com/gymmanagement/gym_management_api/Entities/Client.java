package com.gymmanagement.gym_management_api.Entities;
import java.time.LocalDate;

import com.gymmanagement.gym_management_api.Enums.UserStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idClient;

    User user;

    LocalDate date_of_birth;

    String adress;

    LocalDate registration_date;

    UserStatus status;

    @ManyToOne
    @JoinColumn(name = "membership_id")
    Membership membership;

    TrainingGoal training_goal;
}

