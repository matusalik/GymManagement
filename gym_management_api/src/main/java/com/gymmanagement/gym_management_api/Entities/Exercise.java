package com.gymmanagement.gym_management_api.Entities;
import com.gymmanagement.gym_management_api.Enums.ExerciseCategory;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity

public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer exerciseId;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ExerciseCategory exercise_category;
    
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    private String instructions;
}
