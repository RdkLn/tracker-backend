package dev.rdkln.tracker.workouttemplate.rest;

import java.util.List;

public record ViewWorkoutTemplateDTO(Long id, List<ViewExerciseTemplateDTO> exerciseTemplates, Long creatorId) {

    public record ViewExerciseTemplateDTO(int exercisePriority,int numSets, List<String> sets,String exerciseType){

    }
}
