package com.gymmanagement.gym_management_api.DTO.TrainingPlan;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingPlanCreateDTO {
    public String name;
    public String description;
    public String difficulty_level;
    public Integer training_goal_id;
    public Iterable<Integer> exercises_ids;
}
