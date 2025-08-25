package dev.rdkln.tracker.workouttemplate.domain;

import java.util.List;

import dev.rdkln.tracker.exercisetype.domain.ExerciseType;
import dev.rdkln.tracker.utils.StringListConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ExerciseTemplate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer exercisePriority;

    private Integer numSets;

    @Convert(converter = StringListConverter.class)
    private List<String> sets;

    @ManyToOne
    @JoinColumn(name = "type",nullable = false)
    private ExerciseType exerciseType;

    @ManyToOne
    @JoinColumn(name = "workout_template",nullable = false)
    private WorkoutTemplate workoutTemplate;
}
