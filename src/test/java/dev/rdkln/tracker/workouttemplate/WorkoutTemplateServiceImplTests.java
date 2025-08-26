package dev.rdkln.tracker.workouttemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.rdkln.tracker.exercisetype.ExerciseTypeNotFoundException;
import dev.rdkln.tracker.exercisetype.domain.ExerciseType;
import dev.rdkln.tracker.exercisetype.domain.ExerciseTypeRepository;
import dev.rdkln.tracker.user.domain.UserId;
import dev.rdkln.tracker.workouttemplate.domain.ExerciseTemplate;
import dev.rdkln.tracker.workouttemplate.domain.ExerciseTemplateRepository;
import dev.rdkln.tracker.workouttemplate.domain.WorkoutTemplate;
import dev.rdkln.tracker.workouttemplate.domain.WorkoutTemplateRepository;
import dev.rdkln.tracker.workouttemplate.rest.CreateTemplateDTO;
import dev.rdkln.tracker.workouttemplate.rest.CreateTemplateDTO.CreateExerciseTemplateDTO;

@ExtendWith(MockitoExtension.class)
class WorkoutTemplateServiceImplTests {

    @Mock
    private WorkoutTemplateRepository workoutTemplateRepository;
    @Mock
    private ExerciseTemplateRepository exerciseTemplateRepository;
    @Mock
    private ExerciseTypeRepository exerciseTypeRepository;

    @InjectMocks
    private WorkoutTemplateServiceImpl templateService;

    @Test
    void createTemplateGivenValidTemplateDTO() {

        ExerciseType mockType = ExerciseType.builder().id(1L).build();
        when(exerciseTypeRepository.findById(1L)).thenReturn(Optional.of(mockType));
        when(workoutTemplateRepository.save(any(WorkoutTemplate.class))).thenAnswer(invocation -> {

            WorkoutTemplate argument = invocation.getArgument(0, WorkoutTemplate.class);
            argument.setId(1L);
            return argument;
        });
        when(exerciseTemplateRepository.save(any(ExerciseTemplate.class))).thenAnswer(invocation -> {
            ExerciseTemplate exercise = invocation.getArgument(0, ExerciseTemplate.class);
            exercise.setId(exercise.getExercisePriority().longValue());
            return exercise;
        });

        CreateTemplateDTO dto = new CreateTemplateDTO(new UserId(1L),
                List.of(new CreateExerciseTemplateDTO(1, 3, List.of("6-8", "8-10", "8-10"), 1L),
                        new CreateExerciseTemplateDTO(2, 2, List.of("6-8", "8-10"), 1L)));

        WorkoutTemplate template = templateService.createTemplate(dto);
        verify(exerciseTemplateRepository, times(2)).save(any());
        verify(exerciseTypeRepository, times(2)).findById(1L);
        verify(workoutTemplateRepository, times(1)).save(any());
    }

    @Test
    void expect_throw_When_Given_Invalid_WorkoutType() {
        ExerciseType mockType = ExerciseType.builder().id(1L).build();
        when(exerciseTypeRepository.findById(1L)).thenReturn(Optional.of(mockType));
        when(workoutTemplateRepository.save(any(WorkoutTemplate.class))).thenAnswer(invocation -> {

            WorkoutTemplate argument = invocation.getArgument(0, WorkoutTemplate.class);
            argument.setId(1L);
            return argument;
        });
        when(exerciseTemplateRepository.save(any(ExerciseTemplate.class))).thenAnswer(invocation -> {
            ExerciseTemplate exercise = invocation.getArgument(0, ExerciseTemplate.class);
            exercise.setId(exercise.getExercisePriority().longValue());
            return exercise;
        });

        CreateTemplateDTO dto = new CreateTemplateDTO(new UserId(1L),
                List.of(new CreateExerciseTemplateDTO(1, 3, List.of("6-8", "8-10", "8-10"), 1L),
                        new CreateExerciseTemplateDTO(2, 2, List.of("6-8", "8-10"), 2L)));

        assertThrows(ExerciseTypeNotFoundException.class, () -> templateService.createTemplate(dto));
        verify(exerciseTypeRepository, times(2)).findById(any());
        verify(exerciseTemplateRepository, times(1)).save(any());
        verify(workoutTemplateRepository, times(1)).save(any());

    }
}
