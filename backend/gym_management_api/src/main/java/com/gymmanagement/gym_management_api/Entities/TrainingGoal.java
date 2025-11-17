package com.gymmanagement.gym_management_api.Entities;
import jakarta.persistence.Entity;
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
public class TrainingGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainingGoalId;

    private String name;

    private String description;
}
