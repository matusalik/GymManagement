package com.gymmanagement.gym_management_api.Repositories;

import com.gymmanagement.gym_management_api.Entities.GroupClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface GroupClassRepository extends JpaRepository<GroupClass, Integer> {
    long countByTrainer_UserId(Integer trainerId);
    Iterable<GroupClass>findAllByTrainer_UserId(Integer trainerId);
}
