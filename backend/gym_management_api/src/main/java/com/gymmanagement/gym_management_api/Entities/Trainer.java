package com.gymmanagement.gym_management_api.Entities;
import java.util.List;
import com.gymmanagement.gym_management_api.Enums.UserStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Trainer extends User{
    @ManyToMany
    @JoinTable(
        name = "trainer_specialization",
        joinColumns = @JoinColumn(name = "trainer_id"),
        inverseJoinColumns = @JoinColumn(name = "specialization_id")
    )
    private List<Specialization>specializations;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainerAvailability>availability;

    private String bio;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
