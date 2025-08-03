package dev.rdkln.tracker.exercise.domain;

import java.util.List;

import dev.rdkln.tracker.exerciseType.domain.ExerciseType;
import dev.rdkln.tracker.exerciseset.domain.ExerciseSet;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private List<ExerciseSet> sets;

    private String tips;

    @ManyToOne
    @JoinColumn(name = "type", nullable = false)
    private ExerciseType type;
}
