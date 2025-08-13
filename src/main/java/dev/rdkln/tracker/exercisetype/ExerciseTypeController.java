package dev.rdkln.tracker.exercisetype;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.rdkln.tracker.exercisetype.domain.ExerciseType;
import dev.rdkln.tracker.exercisetype.domain.ExerciseTypeRepository;

@RestController
@RequestMapping("exercise-type")
public class ExerciseTypeController {

    private ExerciseTypeRepository repository;

    public ExerciseTypeController(ExerciseTypeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public ResponseEntity<List<ExerciseType>> listTypes() {
        return ResponseEntity.ok(repository.findAll());
    }

}
