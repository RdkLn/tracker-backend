package dev.rdkln.tracker.workouttemplate;

import java.util.List;

import dev.rdkln.tracker.user.domain.UserId;
import dev.rdkln.tracker.workouttemplate.domain.WorkoutTemplate;
import dev.rdkln.tracker.workouttemplate.rest.CreateTemplateDTO;
import dev.rdkln.tracker.workouttemplate.rest.ViewWorkoutTemplateDTO;

public interface WorkoutTemplateService {

    List<ViewWorkoutTemplateDTO> listTemplates();

    List<ViewWorkoutTemplateDTO> findTemplatesByCreator(UserId userId);

    WorkoutTemplate createTemplate(CreateTemplateDTO dto);

    void deleteTemplate(Long id);


}
