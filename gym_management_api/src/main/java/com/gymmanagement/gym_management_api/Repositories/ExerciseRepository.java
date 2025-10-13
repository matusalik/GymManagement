package com.gymmanagement.gym_management_api.Repositories;

import com.gymmanagement.gym_management_api.Entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
}
