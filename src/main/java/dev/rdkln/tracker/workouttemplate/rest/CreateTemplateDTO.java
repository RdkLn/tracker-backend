package dev.rdkln.tracker.workouttemplate.rest;

import java.util.List;

import dev.rdkln.tracker.user.domain.UserId;

public record CreateTemplateDTO(UserId creator,List<CreateExerciseTemplateDTO> exerciseTemplates) {

    public record CreateExerciseTemplateDTO(Integer exercisePriority,Integer numSets,List<String> sets,Long exerciseTypeId){

    }
}
