package dev.rdkln.tracker.workouttemplate.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.rdkln.tracker.user.domain.UserId;
import dev.rdkln.tracker.workouttemplate.WorkoutTemplateService;
import dev.rdkln.tracker.workouttemplate.domain.WorkoutTemplateRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/worktou-template")
@Slf4j

public class WorkoutTemplateController {

    @Autowired
    private WorkoutTemplateRepository repository;

    private WorkoutTemplateService service;

    public WorkoutTemplateController(WorkoutTemplateService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<ViewWorkoutTemplateDTO> listTemplates() {
        return service.listTemplates();
    }

    @GetMapping("/{creatorId}")
    public List<ViewWorkoutTemplateDTO> fingByCreator(@PathVariable Long creatorId) {
        log.info("Searching for templates created by user: {}",creatorId);
        return service.findTemplatesByCreator(new UserId(creatorId));
    }

}
