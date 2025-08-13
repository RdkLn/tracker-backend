package dev.rdkln.tracker.workoutsession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.rdkln.tracker.user.domain.UserId;
import dev.rdkln.tracker.workoutsession.domain.WorkoutSession;
import dev.rdkln.tracker.workoutsession.domain.WorkoutSessionRepository;
import dev.rdkln.tracker.workoutsession.rest.dto.CreateWorkoutSessionDTO;
import dev.rdkln.tracker.workoutsession.rest.dto.ViewWorkoutSessionDTO;

@ExtendWith(SpringExtension.class)
class WorkoutServiceImplTests {

    @TestConfiguration
    static class WorkoutServiceImplTestContextConfiguration {

        @Bean
        WorkoutService workoutService(WorkoutSessionRepository repository) {
            return new WorkoutServiceImpl(repository);
        }
    }

    @Autowired
    private WorkoutService workoutService;

    @MockitoBean
    private WorkoutSessionRepository repository;

    private List<WorkoutSession> sessions = new ArrayList<>();

    @BeforeEach
    void setUp() {
        sessions = List.of(
                WorkoutSession.builder().id(1L).userId(new UserId(1L)).date(LocalDate.of(2025, 12, 12)).build(),
                WorkoutSession.builder().id(2L).userId(new UserId(1L)).date(LocalDate.of(2025, 12, 12)).build());
    }

    @Test
    void listWorkoutWhenGivenValidUserId() {

        List<ViewWorkoutSessionDTO> expectedResult = List.of(
                new ViewWorkoutSessionDTO(1L, LocalDate.of(2025, 12, 12), 1L, new ArrayList<>()),
                new ViewWorkoutSessionDTO(2L, LocalDate.of(2025, 12, 12), 1L, new ArrayList<>()));

        UserId id = new UserId(1L);
        when(repository.findAllByUserIdId(id)).thenReturn(sessions);
        assertEquals(expectedResult, workoutService.listWorkouts(id));
    }

    @Test
    void shouldCreateWorkoutGivenValidDto() {
        when(repository.save(any(WorkoutSession.class))).thenReturn(sessions.get(0));
        CreateWorkoutSessionDTO createDto = new CreateWorkoutSessionDTO(LocalDate.of(20025, 12, 12), 1L);
        assertEquals(new ViewWorkoutSessionDTO(1L, LocalDate.of(2025, 12, 12), 1L,
                new ArrayList<>()), workoutService.createWorkout(createDto));
    }

    @Test
    void shouldDeleteWorkoutGivenValidSessinIdAndUserId() {
        Long sessionId = 1L;
        UserId userId = new UserId(1L);
        when(repository.findById(sessionId)).thenReturn(Optional.of(sessions.get(0)));
        workoutService.deleteWorkout(sessionId, userId);

        verify(repository).findById(sessionId);
        verify(repository).delete(sessions.get(0));
    }

    @Test
    void sholdThrowExceptionWhenGivenInvalidSessionIdAndValidUserId() {
        Long sessionId = 999L;
        UserId userId = new UserId(1L);
        when(repository.findById(sessionId)).thenReturn(Optional.empty());
        assertThrows(WorkoutSessionNotFoundException.class, () -> workoutService.deleteWorkout(sessionId, userId));
    }

    @Test
    void sholdThrowExceptionWhenGivenValidSessionIdAndInvalidUserId() {
        Long sessionId = 1L;
        UserId userId = new UserId(999L);
        when(repository.findById(sessionId)).thenReturn(Optional.of(sessions.get(0)));
        assertThrows(WorkoutSessionNotFoundException.class, () -> workoutService.deleteWorkout(sessionId, userId));
    }

}
