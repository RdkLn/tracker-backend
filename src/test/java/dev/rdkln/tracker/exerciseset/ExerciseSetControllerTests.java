package dev.rdkln.tracker.exerciseset;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.rdkln.tracker.exerciseset.domain.ExerciseSet;
import dev.rdkln.tracker.exerciseset.domain.ExerciseSetRepository;

@WebMvcTest(controllers = ExerciseSetController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc
class ExerciseSetControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ExerciseSetRepository exerciseSetRepository;

    private List<ExerciseSet> sets = new ArrayList<>();

    @BeforeEach
    void setUp() {
        sets = List.of(ExerciseSet.builder().id(1L).reps(5).weight(20.0).notes("Notes").exercise(null).build(),
                ExerciseSet.builder().id(2L).reps(7).weight(40.0).notes("Notes").exercise(null).build());
    }

    @Test
    void listAllExerciseSets() throws Exception {
        when(exerciseSetRepository.findAll()).thenReturn(sets);
        mockMvc.perform(get("/exercise-set"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void shouldFindSetWhenGivenValidId() throws Exception {
        when(exerciseSetRepository.findById(1L)).thenReturn(Optional.of(sets.get(0)));
        String expectedResult = """
                {
                    "id": 1,
                    "reps": 5,
                    "weight": 20.0,
                    "notes": Notes,
                    "exercise": null
                  }
                """;
        mockMvc.perform(get("/exercise-set/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    void shouldntFindSetWhenGivenInvalidId() throws Exception {
        when(exerciseSetRepository.findById(999L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/exercise-set/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateEntityWhenGivenValidRequest() throws Exception {
        ExerciseSet payload = ExerciseSet.builder().id(3L).reps(7).weight(40.0).notes("Notes").exercise(null).build();
        ObjectMapper om = new ObjectMapper();
        mockMvc.perform(post("/exercise-set")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(payload)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateEntityWhenGivenValidRequest() throws Exception {
        ExerciseSet payload = ExerciseSet.builder().id(1L).reps(7).weight(50.0).notes("Notes").exercise(null).build();
        ObjectMapper om = new ObjectMapper();
        mockMvc.perform(put("/exercise-set/1L")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(payload)))
                .andExpect(status().isOk());
    }
}
