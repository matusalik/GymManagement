package com.gymmanagement.gym_management_api.Repositories;

import com.gymmanagement.gym_management_api.Entities.TrainingGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingGoalRepository extends JpaRepository<TrainingGoal, Integer> {
}
