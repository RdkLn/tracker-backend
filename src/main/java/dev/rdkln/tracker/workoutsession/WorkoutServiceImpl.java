package dev.rdkln.tracker.workoutsession;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.rdkln.tracker.exercise.domain.Exercise;
import dev.rdkln.tracker.user.domain.UserId;
import dev.rdkln.tracker.workoutsession.domain.WorkoutSession;
import dev.rdkln.tracker.workoutsession.domain.WorkoutSessionRepository;
import dev.rdkln.tracker.workoutsession.rest.WorkoutSessionNotFoundException;
import dev.rdkln.tracker.workoutsession.rest.dto.CreateWorkoutSessionDTO;
import dev.rdkln.tracker.workoutsession.rest.dto.ViewWorkoutSessionDTO;
import dev.rdkln.tracker.workoutsession.rest.dto.ViewWorkoutSessionDTO.ViewExcerciseTypeDTO;
import dev.rdkln.tracker.workoutsession.rest.dto.ViewWorkoutSessionDTO.ViewExerciseSetDTO;
import dev.rdkln.tracker.workoutsession.rest.dto.ViewWorkoutSessionDTO.ViewExercisesDTO;

@Service("workoutServiceImpl")
public class WorkoutServiceImpl implements WorkoutService {

    private WorkoutSessionRepository repository;

    public WorkoutServiceImpl(WorkoutSessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public ViewWorkoutSessionDTO createWorkout(CreateWorkoutSessionDTO dto) {
        WorkoutSession session = WorkoutSession.builder().id(dto.userId()).date(dto.date().atStartOfDay()).build();
        return mapEntityToDto(repository.save(session));
    }

    @Override
    public void deleteWorkout(Long sessionId, UserId userId) {
        Optional<WorkoutSession> entry = repository.findById(sessionId);
        if (entry.isPresent() && entry.get().getUserId().id().equals(userId.id())) {
            repository.delete(entry.get());
        } else {
            throw new WorkoutSessionNotFoundException(sessionId.toString());
        }
    }

    @Override
    public void addExercise(Long workoutId, Exercise exercise) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addExercise'");
    }

    @Override
    public List<ViewWorkoutSessionDTO> listWorkouts(UserId userId) {
        List<WorkoutSession> entries = repository.findAllByUserId(userId);
        return entries.stream().map(this::mapEntityToDto).toList();
    }

    @Override
    public ViewWorkoutSessionDTO findWorkoutByDate(UserId userId, LocalDate date) {
        Optional<WorkoutSession> entry = repository.findByUserAndDate(userId.id(), date.atStartOfDay(),date.atStartOfDay().plusDays(1));
        if (entry.isEmpty())
            throw new WorkoutSessionNotFoundException(userId, date);
        return mapEntityToDto(entry.get());
    }

    ViewWorkoutSessionDTO mapEntityToDto(WorkoutSession session) {
        List<ViewExercisesDTO> exericisesDto = session.getExercises().stream()
                .map(ex -> new ViewExercisesDTO(ex.getId(), ex.getSets().stream()
                        .map(set -> new ViewExerciseSetDTO(set.getId(), set.getReps(), set.getWeight(), set.getNotes()))
                        .toList(),
                        ex.getTips(), new ViewExcerciseTypeDTO(ex.getType().getId(), ex.getType().getName(),
                                ex.getType().getDescription())))
                .toList();
        return new ViewWorkoutSessionDTO(session.getId(), session.getDate().toLocalDate(), session.getUserId().id(),
                exericisesDto);
    }
}
