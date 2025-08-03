package dev.rdkln.tracker.workoutsession.domain;

import java.time.LocalDate;
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
import lombok.Data;

@Data
@Entity
public class Workoutsession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    private UserId userId;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<Exercise> exercises;

}
