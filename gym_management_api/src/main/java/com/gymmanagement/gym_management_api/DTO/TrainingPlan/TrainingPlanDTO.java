package com.gymmanagement.gym_management_api.DTO.TrainingPlan;

import com.gymmanagement.gym_management_api.DTO.Exercise.ExerciseDTO;
import com.gymmanagement.gym_management_api.DTO.TrainingGoal.TrainingGoalDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrainingPlanDTO {
    public String name;
    public String description;
    public String difficulty_level;
    public TrainingGoalDTO training_goal;
    public Iterable<ExerciseDTO> exercises;
}
