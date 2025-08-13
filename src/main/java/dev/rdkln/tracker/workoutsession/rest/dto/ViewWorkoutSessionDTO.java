package dev.rdkln.tracker.workoutsession.rest.dto;

import java.time.LocalDate;
import java.util.List;

public record ViewWorkoutSessionDTO(Long id, LocalDate date, Long userId, List<ViewExercisesDTO> exercises) {

    public record ViewExerciseSetDTO(Long id, int reps, Double weight, String notes) {

    }

    public record ViewExercisesDTO(Long id, List<ViewExerciseSetDTO> sets, String tips,
            ViewExcerciseTypeDTO exerciseType) {

    }

    public record ViewExcerciseTypeDTO(Long id, String name, String description) {

    }

}
