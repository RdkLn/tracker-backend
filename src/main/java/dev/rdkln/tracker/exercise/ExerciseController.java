package dev.rdkln.tracker.exercise;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.rdkln.tracker.exercise.domain.Exercise;
import dev.rdkln.tracker.exercise.domain.ExerciseRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseRepository repository;

    ExerciseController(ExerciseRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Exercise> listExercises() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Exercise> getMethodName(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateExercise(@PathVariable Long id, @RequestBody Exercise updateDto) {
        repository.save(updateDto);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("")
    public Exercise postMethodName(@RequestBody @Valid Exercise entry) {
        return repository.save(entry);
    }
}
