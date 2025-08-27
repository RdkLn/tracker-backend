package dev.rdkln.tracker.exercisetype.domain;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseTypeRepository extends ListCrudRepository<ExerciseType, Long> {

}
