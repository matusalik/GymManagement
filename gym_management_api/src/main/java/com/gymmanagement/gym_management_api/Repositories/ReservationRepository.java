package com.gymmanagement.gym_management_api.Repositories;

import com.gymmanagement.gym_management_api.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
