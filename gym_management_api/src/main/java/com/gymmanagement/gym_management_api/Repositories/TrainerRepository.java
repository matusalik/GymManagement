package com.gymmanagement.gym_management_api.Repositories;


import com.gymmanagement.gym_management_api.Entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
}
