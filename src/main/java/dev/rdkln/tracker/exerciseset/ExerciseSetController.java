package dev.rdkln.tracker.exerciseset;

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

import dev.rdkln.tracker.exerciseset.domain.ExerciseSet;
import dev.rdkln.tracker.exerciseset.domain.ExerciseSetRepository;



@RestController
@RequestMapping("/exercise-set")
public class ExerciseSetController {

    private ExerciseSetRepository repository;

    ExerciseSetController(ExerciseSetRepository exericseSetRepository) {
        this.repository = exericseSetRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<ExerciseSet>> listSets() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseSet> findById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ExerciseSet postMethodName(@RequestBody ExerciseSet entry) {
        return repository.save(entry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseSet> updateExerciseSet(@PathVariable String id, @RequestBody ExerciseSet entity) {
        repository.save(entity);
        return ResponseEntity.ok().build();
    }
}
