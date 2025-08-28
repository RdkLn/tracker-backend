package dev.rdkln.tracker.workoutsession.rest;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import dev.rdkln.tracker.user.domain.UserId;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class WorkoutSessionNotFoundException extends RuntimeException {

    public WorkoutSessionNotFoundException(String sessionId) {
        super(String.format("Workout with id: %s not found:", sessionId));
    }

    public WorkoutSessionNotFoundException(UserId userId, LocalDate date) {
        super(String.format("Workout with userId: %s and date: %s not found:", userId.id(), date));
    }

}
