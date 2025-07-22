package com.gymmanagement.gym_management_api.Entities;
import java.time.LocalDate;

import com.gymmanagement.gym_management_api.Enums.UserStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Client extends User{
    private LocalDate date_of_birth;

    private String address;

    private LocalDate registration_date;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @ManyToOne
    @JoinColumn(name = "membership_id")
    private Membership membership;

    @ManyToOne
    @JoinColumn(name = "training_goal_id")
    private TrainingGoal training_goal;
}

