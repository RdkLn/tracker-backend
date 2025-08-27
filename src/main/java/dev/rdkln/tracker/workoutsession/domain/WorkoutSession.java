package dev.rdkln.tracker.workoutsession.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dev.rdkln.tracker.exercise.domain.Exercise;
import dev.rdkln.tracker.user.domain.UserId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    private UserId userId;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE, mappedBy = "workoutSession")
    @Default
    private List<Exercise> exercises = new ArrayList<>();

}
