package dev.rdkln.tracker.workouttemplate.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.rdkln.tracker.user.domain.UserId;

@Repository
public interface WorkoutTemplateRepository extends JpaRepository<WorkoutTemplate,Long> {

    List<WorkoutTemplate> findByCreator(UserId creator);

}
