package com.gymmanagement.gym_management_api.Repositories;

import com.gymmanagement.gym_management_api.Entities.Review;
import com.gymmanagement.gym_management_api.Entities.TrainerAvailability;
import com.gymmanagement.gym_management_api.Enums.DayOfTheWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrainerAvailabilityRepository extends JpaRepository<TrainerAvailability, Integer> {
    Iterable<TrainerAvailability>findAllByTrainer_UserId(Integer trainerId);

    @Query(
            value = """
        SELECT EXISTS (
            SELECT 1
            FROM trainer_availability
            WHERE trainer_id = :trainer_id
              AND day_of_the_week = :day_of_the_week
        )
        """,
            nativeQuery = true
    )
    boolean existsByTrainerIdAndDayOfTheWeek(@Param("trainer_id") Integer trainer_id, @Param("day_of_the_week") String day_of_the_week);
}
