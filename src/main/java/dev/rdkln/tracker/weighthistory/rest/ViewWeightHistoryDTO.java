package dev.rdkln.tracker.weighthistory.rest;

import java.time.LocalDate;

public record ViewWeightHistoryDTO(Double weight, LocalDate weighInDate) {

}
