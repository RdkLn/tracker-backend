package dev.rdkln.tracker.workoutsession.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.rdkln.tracker.user.domain.UserId;
import dev.rdkln.tracker.workoutsession.WorkoutService;
import dev.rdkln.tracker.workoutsession.rest.dto.CreateWorkoutSessionDTO;
import dev.rdkln.tracker.workoutsession.rest.dto.ViewWorkoutSessionDTO;

@RestController
@RequestMapping("workouts")
public class WorkoutSessionController {

    private WorkoutService workoutService;

    public WorkoutSessionController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping("")
    public ResponseEntity<ViewWorkoutSessionDTO> findSessionByDate(@RequestParam LocalDate date,
            @RequestParam Long userId) {
        return ResponseEntity.ok(workoutService.findWorkoutByDate(new UserId(userId), date));
    }

    @GetMapping("list")
    public ResponseEntity<List<ViewWorkoutSessionDTO>> list(@RequestParam Long userId) {
        return ResponseEntity.ok(workoutService.listWorkouts(new UserId(userId)));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteWorkoutSession(@PathVariable Long id, @RequestParam Long userId) {
        workoutService.deleteWorkout(id, new UserId(userId));
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ViewWorkoutSessionDTO postMethodName(@RequestBody CreateWorkoutSessionDTO dto) {
        return workoutService.createWorkout(dto);
    }

}
