package dev.rdkln.tracker.exercisetype;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ExerciseTypeNotFoundException extends RuntimeException {

    public ExerciseTypeNotFoundException(Long id){
        super(String.format("ExerciseType with id: %s not found:", id));
    }
}
