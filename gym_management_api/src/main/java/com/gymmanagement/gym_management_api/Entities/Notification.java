package com.gymmanagement.gym_management_api.Entities;
import java.time.LocalDate;

import com.gymmanagement.gym_management_api.Enums.NotificationStatus;

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
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer notificationId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    LocalDate sent_date;

    String subject;

    String content;

    NotificationStatus status;
}
