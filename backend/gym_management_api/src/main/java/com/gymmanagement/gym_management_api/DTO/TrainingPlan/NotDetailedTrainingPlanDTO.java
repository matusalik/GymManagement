package com.gymmanagement.gym_management_api.DTO.TrainingPlan;

import com.gymmanagement.gym_management_api.DTO.Exercise.NotDetailedExerciseDTO;
import com.gymmanagement.gym_management_api.DTO.TrainingGoal.TrainingGoalDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotDetailedTrainingPlanDTO {
    public String name;
    public String description;
    public String difficulty_level;
    public TrainingGoalDTO training_goal;
    public Iterable<NotDetailedExerciseDTO>exercises;
}
