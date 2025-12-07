package com.gymmanagement.gym_management_api.Repositories;

import com.gymmanagement.gym_management_api.Entities.Review;
import com.gymmanagement.gym_management_api.Entities.TrainerAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerAvailabilityRepository extends JpaRepository<TrainerAvailability, Integer> {
    Iterable<TrainerAvailability>findAllByTrainer_UserId(Integer trainerId);
}
