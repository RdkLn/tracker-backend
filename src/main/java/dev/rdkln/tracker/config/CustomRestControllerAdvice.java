package dev.rdkln.tracker.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.rdkln.tracker.exercisetype.ExerciseTypeNotFoundException;
import dev.rdkln.tracker.workoutsession.rest.WorkoutSessionNotFoundException;
import jakarta.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class CustomRestControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(HttpServletRequest req,MethodArgumentNotValidException ex){
        ProblemDetail problemDetail=ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        problemDetail.setTitle("Valdation errror");
        return problemDetail;
    }

    @ExceptionHandler({WorkoutSessionNotFoundException.class,ExerciseTypeNotFoundException.class})
    public ProblemDetail handleEntityNotFoundException(HttpServletRequest req,RuntimeException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setDetail(ex.getLocalizedMessage());
        problemDetail.setTitle("Not found error");
        return problemDetail;
    }

}
