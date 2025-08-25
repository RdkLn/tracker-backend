package dev.rdkln.tracker.workouttemplate.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.rdkln.tracker.workouttemplate.WorkoutTemplateService;


@RestController
@RequestMapping("/worktou-template")

public class WorkoutTemplateController{

    private WorkoutTemplateService service;

    public WorkoutTemplateController(WorkoutTemplateService service){
        this.service=service;
    }

    @GetMapping("")
    public List<ViewWorkoutTemplateDTO> listTemplates() {
        return service.listTemplates();
    }

}
