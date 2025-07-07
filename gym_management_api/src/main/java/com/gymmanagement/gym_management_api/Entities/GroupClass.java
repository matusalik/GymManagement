package com.gymmanagement.gym_management_api.Entities;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity

public class GroupClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupClassId;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToMany
    @JoinTable(
        name = "group_class_client",
        joinColumns = @JoinColumn(name = "group_class_id"),
        inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client>client_list;

    private LocalDateTime date_time;

    private Integer max_participants;
}
