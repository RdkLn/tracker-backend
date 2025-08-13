package dev.rdkln.tracker.exercise.domain;

import java.util.ArrayList;
import java.util.List;

import dev.rdkln.tracker.exerciseset.domain.ExerciseSet;
import dev.rdkln.tracker.exercisetype.domain.ExerciseType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "exercise")
    @Default
    private List<ExerciseSet> sets = new ArrayList<>();

    private String tips;

    @ManyToOne
    @JoinColumn(name = "type", nullable = false)
    @NotNull(message = "ExerciseType cannot be null")
    private ExerciseType type;
}
