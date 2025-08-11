package dev.rdkln.tracker.exerciseset.domain;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseSetRepository extends ListCrudRepository<ExerciseSet, Long> {

}
