package dev.rdkln.tracker.exerciseset.domain;

import dev.rdkln.tracker.exercise.domain.Exercise;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ExerciseSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int reps;

    private Double weight;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;
}
