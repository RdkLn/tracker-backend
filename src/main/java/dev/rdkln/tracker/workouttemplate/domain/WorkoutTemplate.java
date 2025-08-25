package dev.rdkln.tracker.workouttemplate.domain;

import java.util.List;

import dev.rdkln.tracker.user.domain.UserId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class WorkoutTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Embedded
    @AttributeOverride(name = "id", column =  @Column(name = "creator"))
    private UserId creator;

    @OneToMany
    private List<ExerciseTemplate> exercises;


}
