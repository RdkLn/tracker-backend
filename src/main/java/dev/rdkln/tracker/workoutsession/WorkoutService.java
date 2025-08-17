package dev.rdkln.tracker.workoutsession;

import java.time.LocalDate;
import java.util.List;

import dev.rdkln.tracker.exercise.domain.Exercise;
import dev.rdkln.tracker.user.domain.UserId;
import dev.rdkln.tracker.workoutsession.rest.dto.CreateWorkoutSessionDTO;
import dev.rdkln.tracker.workoutsession.rest.dto.ViewWorkoutSessionDTO;

public interface WorkoutService {

    ViewWorkoutSessionDTO createWorkout(CreateWorkoutSessionDTO dto);

    void deleteWorkout(Long sessionId, UserId userId);

    void addExercise(Long sessionId, Exercise exercise);

    List<ViewWorkoutSessionDTO> listWorkouts(UserId userId);

    ViewWorkoutSessionDTO findWorkoutByDate(UserId userId, LocalDate date);

}
