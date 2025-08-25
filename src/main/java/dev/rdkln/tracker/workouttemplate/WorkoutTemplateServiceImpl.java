package dev.rdkln.tracker.workouttemplate;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
import dev.rdkln.tracker.workouttemplate.rest.ViewWorkoutTemplateDTO;
import dev.rdkln.tracker.workouttemplate.rest.ViewWorkoutTemplateDTO.ViewExerciseTemplateDTO;
import jakarta.transaction.Transactional;

@Service("workoutTemplateServiceImpl")
public class WorkoutTemplateServiceImpl implements WorkoutTemplateService {

    private WorkoutTemplateRepository workoutTemplateRepository;
    private ExerciseTemplateRepository exerciseTemplateRepository;
    private ExerciseTypeRepository exerciseTypeRepository;

    public WorkoutTemplateServiceImpl(WorkoutTemplateRepository wtRepository,
            ExerciseTemplateRepository esRepository,
            ExerciseTypeRepository etRepository) {
        this.workoutTemplateRepository = wtRepository;
        this.exerciseTemplateRepository = esRepository;
        this.exerciseTypeRepository = etRepository;
    }

    @Override
    public List<ViewWorkoutTemplateDTO> listTemplates() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listTemplates'");
    }

    @Override
    @Transactional
    public WorkoutTemplate createTemplate(CreateTemplateDTO dto) {
        //TODO: Change to batch
        WorkoutTemplate wtemplate = new WorkoutTemplate();
        wtemplate.setCreator(dto.creator());
        WorkoutTemplate workoutEntry = workoutTemplateRepository.save(wtemplate);
        for (CreateExerciseTemplateDTO template : dto.exerciseTemplates()) {
            ExerciseTemplate exerciseEntry = new ExerciseTemplate();
            exerciseEntry.setExercisePriority(template.exercisePriority());
            exerciseEntry.setNumSets(template.numSets());
            exerciseEntry.setSets(template.sets());
            //TODO: Maybe change this lookup
            exerciseEntry.setExerciseType(findExerciseType(template.exerciseTypeId()));
            ExerciseTemplate save = exerciseTemplateRepository.save(exerciseEntry);
        }
        return workoutEntry;
    }

    @Override
    public void deleteTemplate(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTemplate'");
    }

    private ExerciseType findExerciseType(Long id){
        Optional<ExerciseType> type = exerciseTypeRepository.findById(id);
        if(type.isEmpty()){
            throw new ExerciseTypeNotFoundException(id);
        }
        return type.get();
    }

    @Override
    public List<ViewWorkoutTemplateDTO> findTemplatesByCreator(UserId userId) {
        List<WorkoutTemplate> entries = workoutTemplateRepository.findByCreator(userId);
        return entries.stream()
                .map(entry -> new ViewWorkoutTemplateDTO(entry.getId(), entry.getExercises().stream()
                        .map(exercise -> new ViewExerciseTemplateDTO(exercise.getExercisePriority(),
                                exercise.getNumSets(), exercise.getSets(), exercise.getExerciseType().getName()))
                        .toList(), entry.getCreator().id()))
                .toList();
    }

}
