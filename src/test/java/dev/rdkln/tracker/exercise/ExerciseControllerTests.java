package dev.rdkln.tracker.exercise;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.rdkln.tracker.exercise.domain.Exercise;
import dev.rdkln.tracker.exercise.domain.ExerciseRepository;
import dev.rdkln.tracker.exercisetype.domain.ExerciseType;

@WebMvcTest(controllers = ExerciseController.class, excludeAutoConfiguration = (SecurityAutoConfiguration.class))
@AutoConfigureMockMvc
class ExerciseControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ExerciseRepository exerciseRepository;

    List<Exercise> exercises = new ArrayList<>();

    @BeforeEach
    void setUp() {
        exercises = List.of(Exercise.builder().id(1L).sets(List.of()).tips("Tips for the exercise")
                .type(ExerciseType.builder().id(1L).build()).build(),
                Exercise.builder().id(2L).sets(List.of()).tips("Tips for the exercise")
                        .type(ExerciseType.builder().id(2L).build()).build());
    }

    @Test
    void shouldFindAllExericses() throws Exception {
        when(exerciseRepository.findAll()).thenReturn(exercises);
        mockMvc.perform(get("/exercise")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    // GET /exercise/1
    @Test
    void shouldFindExerciseWhenGivenValidId() throws Exception {
        Exercise exercise = exercises.get(0);
        String expectedResult = """
                  {
                    "id": 1,
                    "sets": [],
                    "tips": "Tips for the exercise",
                    "type": {
                      "id": 1
                    }
                  }
                """;
        when(exerciseRepository.findById(1L)).thenReturn(Optional.of(exercises.get(0)));
        mockMvc.perform(get("/exercise/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    // GET /exercise/999
    @Test
    void shouldntFindExerciseWhenGivenInvalidId() throws Exception {
        when(exerciseRepository.findById(999L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/exercise/999"))
                .andExpect(status().isNotFound());
    }

    // POST /exercise
    @Test
    void shouldCreateEntityWhenGivenValidRequest() throws Exception {
        var exercise = Exercise.builder().id(3L).sets(List.of()).tips("Tips for the exercise")
                .type(ExerciseType.builder().id(1L).build()).build();
        when(exerciseRepository.save(exercise)).thenReturn(exercise);
        ObjectMapper om = new ObjectMapper();
        mockMvc.perform(post("/exercise")
                .contentType("application/json")
                .content(om.writeValueAsString(exercise)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldntCreateEntityWhenGivenInvalidRequest() throws Exception {
        var exercise = Exercise.builder().id(3L).sets(List.of()).tips("Tips for the exercise")
                .type(null).build();
        ObjectMapper om = new ObjectMapper();
        mockMvc.perform(post("/exercise")
                .contentType("application/json")
                .content(om.writeValueAsString(exercise)))
                .andExpect(status().isBadRequest());
    }
}
