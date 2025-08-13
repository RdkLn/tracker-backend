package dev.rdkln.tracker.workoutsession.rest.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record CreateWorkoutSessionDTO(@NotNull LocalDate date, @NotNull Long userId) {

}
