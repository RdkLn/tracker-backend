package dev.rdkln.tracker.weighthistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.rdkln.tracker.user.domain.UserId;
import dev.rdkln.tracker.weighthistory.domain.WeightHistory;
import dev.rdkln.tracker.weighthistory.domain.WeightHistoryRepository;
import dev.rdkln.tracker.weighthistory.rest.dto.CreateWeightHistoryDTO;

@ExtendWith(MockitoExtension.class)
public class WeightHistoryServiceImplTests {

    @Mock
    WeightHistoryRepository weightRepository;

    @InjectMocks
    WeightHistoryServiceImpl weightService;

    @Test
    void should_create_weightHistory_given_valid_params(){
        CreateWeightHistoryDTO dto= new CreateWeightHistoryDTO(LocalDate.of(2021, 12, 19),20.0);
        UserId id= new UserId(1L);

        when(weightRepository.save(any(WeightHistory.class))).thenAnswer(invocation -> {
            WeightHistory entry = invocation.getArgument(0,WeightHistory.class);
            entry.setId(1L);
            return entry;
        });

        WeightHistory actual = weightService.addWeight(dto, id);
        assertEquals(1L,actual.getId());
        assertEquals(LocalDate.of(2021, 12, 19),actual.getWeighInDate().toLocalDate());
    }
    @Test
    void should_create_weightHistory_given_invalid_Weight(){
        CreateWeightHistoryDTO dto= new CreateWeightHistoryDTO(null,20.0);
        UserId id= new UserId(1L);

        when(weightRepository.save(any(WeightHistory.class))).thenAnswer(invocation -> {
            WeightHistory entry = invocation.getArgument(0,WeightHistory.class);
            entry.setId(1L);
            return entry;
        });

        WeightHistory actual = weightService.addWeight(dto, id);
        assertEquals(1L,actual.getId());
        assertNotEquals(null,actual.getWeighInDate().toLocalDate());
    }
}
