package dev.rdkln.tracker.workoutsession;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class WorkoutSessionNotFoundException extends RuntimeException {

    public WorkoutSessionNotFoundException(String sessionId) {
        super(String.format("Workout with id: %s not found:", sessionId));
    }

}
