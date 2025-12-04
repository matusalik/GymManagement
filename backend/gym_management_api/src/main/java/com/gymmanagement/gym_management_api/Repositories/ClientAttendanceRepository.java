package com.gymmanagement.gym_management_api.Repositories;

import com.gymmanagement.gym_management_api.Entities.ClientAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientAttendanceRepository extends JpaRepository<ClientAttendance, Integer> {
    long countByClient_UserId(Integer clientId);
    Iterable<ClientAttendance>findAllByClient_UserId(Integer clientId);
}
