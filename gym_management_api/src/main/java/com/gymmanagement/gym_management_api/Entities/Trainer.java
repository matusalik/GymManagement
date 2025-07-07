package com.gymmanagement.gym_management_api.Entities;
import java.util.List;
import com.gymmanagement.gym_management_api.Enums.UserStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer trainerId;

    User user;

    List<Specialization>specializations;

    List<TrainerAvailability>availability;

    String bio;

    UserStatus status;
}
