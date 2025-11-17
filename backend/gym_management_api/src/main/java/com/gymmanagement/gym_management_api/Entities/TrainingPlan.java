package com.gymmanagement.gym_management_api.Entities;
import java.util.List;
import com.gymmanagement.gym_management_api.Enums.TrainingDifficultyLevel;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class TrainingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainingPlanId;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private TrainingDifficultyLevel difficulty_level;

    @ManyToOne
    @JoinColumn(name = "training_goal_id")
    private TrainingGoal training_goal;

    @ManyToMany
    @JoinTable(
        name = "training_plan_exercise",
        joinColumns = @JoinColumn(name = "training_plan_id"),
        inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<Exercise>exercises;
}
