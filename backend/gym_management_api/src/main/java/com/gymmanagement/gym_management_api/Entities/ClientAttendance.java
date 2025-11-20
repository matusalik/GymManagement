package com.gymmanagement.gym_management_api.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ClientAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientAttendanceId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private LocalDateTime date_time;
}
