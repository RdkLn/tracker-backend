package dev.rdkln.tracker.workoutsession.domain;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.rdkln.tracker.user.domain.UserId;

@Repository
public interface WorkoutSessionRepository extends ListCrudRepository<WorkoutSession, Long> {

    List<WorkoutSession> findAllByUserIdId(UserId id);

}
