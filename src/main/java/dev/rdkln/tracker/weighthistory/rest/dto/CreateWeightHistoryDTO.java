package dev.rdkln.tracker.weighthistory.rest.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record CreateWeightHistoryDTO(LocalDate date,@NotNull Double weight) {

}
