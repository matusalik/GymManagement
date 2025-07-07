package com.gymmanagement.gym_management_api.Entities;

import java.time.LocalDateTime;

import com.gymmanagement.gym_management_api.Enums.ReservationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer reservationId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "group_class_id")
    GroupClass groupClass;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    Trainer trainer;

    LocalDateTime date_time;

    ReservationStatus reservation_status;
}
