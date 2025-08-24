package dev.rdkln.tracker.weighthistory.rest;

import java.time.LocalDate;

public record CreateWeightHistoryDTO(LocalDate date, Double weight) {

}
