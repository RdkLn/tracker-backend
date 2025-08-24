package dev.rdkln.tracker.weighthistory.rest.dto;

import java.time.LocalDate;

public record ViewWeightHistoryDTO(Double weight, LocalDate weighInDate) {

}
