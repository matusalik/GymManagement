package com.gymmanagement.gym_management_api.Repositories;

import com.gymmanagement.gym_management_api.Entities.TrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, Integer> {
}
