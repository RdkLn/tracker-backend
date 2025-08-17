package dev.rdkln.tracker.workoutsession.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.rdkln.tracker.user.domain.UserId;

@Repository
public interface WorkoutSessionRepository extends ListCrudRepository<WorkoutSession, Long> {

    List<WorkoutSession> findAllByUserId(UserId id);

    Optional<WorkoutSession> findFirstByUserIdAndDate(UserId userId, LocalDate date);

}
