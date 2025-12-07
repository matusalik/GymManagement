package com.gymmanagement.gym_management_api.Repositories;

import com.gymmanagement.gym_management_api.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    long countByTrainer_UserId(Integer trainerId);
    Iterable<Review>findAllByTrainer_UserId(Integer trainerId);
    Iterable<Review>findTop3ByOrderByDateDesc();
}
