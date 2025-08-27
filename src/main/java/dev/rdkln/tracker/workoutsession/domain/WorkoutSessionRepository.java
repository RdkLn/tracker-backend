package dev.rdkln.tracker.workoutsession.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.rdkln.tracker.user.domain.UserId;

@Repository
public interface WorkoutSessionRepository extends ListCrudRepository<WorkoutSession, Long> {

    List<WorkoutSession> findAllByUserId(UserId id);

    @Query("""
            select ws from WorkoutSession ws
            where ws.userId.id = :userId
             and ws.date between :startDate and :endDate
            """)
    Optional<WorkoutSession> findByUserAndDate(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("""
            select ws.date
             from WorkoutSession ws
             where ws.userId=:userId
            """)
    List<LocalDateTime> findDaysWorkedOut(UserId userId);
}
