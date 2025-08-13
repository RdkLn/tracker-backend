package dev.rdkln.tracker.workoutsession.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.rdkln.tracker.workoutsession.WorkoutService;

@RestController
@RequestMapping("workout")
public class WorkoutController {

    private WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }
}
