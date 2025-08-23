package dev.rdkln.tracker.weighthistory;

import java.time.LocalDate;
import java.util.List;

import dev.rdkln.tracker.user.domain.UserId;
import dev.rdkln.tracker.weighthistory.domain.WeightHistory;
import dev.rdkln.tracker.weighthistory.rest.CreateWeightHistoryDTO;
import dev.rdkln.tracker.weighthistory.rest.ViewWeightHistoryDTO;

public interface WeightHistoryService {

    List<ViewWeightHistoryDTO> listWeight(UserId user);

    WeightHistory addWeight(CreateWeightHistoryDTO dto,UserId userId);

    WeightHistory updateWeight(LocalDate date,Double weight,UserId userId);

    void deleteWeight(UserId id,LocalDate date);

}
