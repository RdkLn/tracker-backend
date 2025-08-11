package dev.rdkln.tracker.exercise.domain;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends ListCrudRepository<Exercise, Long> {

}
