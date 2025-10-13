package com.gymmanagement.gym_management_api.Repositories;

import com.gymmanagement.gym_management_api.Entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
}
