package dev.rdkln.tracker.weighthistory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.rdkln.tracker.user.domain.UserId;
import dev.rdkln.tracker.weighthistory.domain.WeightHistory;
import dev.rdkln.tracker.weighthistory.domain.WeightHistoryRepository;
import dev.rdkln.tracker.weighthistory.rest.dto.CreateWeightHistoryDTO;
import dev.rdkln.tracker.weighthistory.rest.dto.ViewWeightHistoryDTO;

@Service("weightHistoryServiceImpl")
public class WeightHistoryServiceImpl implements WeightHistoryService{

    private WeightHistoryRepository repository;

    public WeightHistoryServiceImpl(WeightHistoryRepository repository){
        this.repository=repository;
    }

    @Override
    public List<ViewWeightHistoryDTO> listWeight(UserId user) {
        return repository.findByUserId(user).stream().map(entry->new ViewWeightHistoryDTO(entry.getWeight(),entry.getWeighInDate().toLocalDate())).toList();
    }

    @Override
    public WeightHistory addWeight(CreateWeightHistoryDTO dto, UserId userId) {
        //TODO: Verify user
        LocalDateTime weighInDate=dto.date()!=null?dto.date().atStartOfDay():LocalDate.now().atStartOfDay();
        WeightHistory entry= new WeightHistory(userId,weighInDate, dto.weight());
        return repository.save(entry);
    }

    @Override
    public WeightHistory updateWeight(LocalDate date, Double weight, UserId userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateWeight'");
    }

    @Override
    public void deleteWeight(UserId id, LocalDate date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteWeight'");
    }

}
