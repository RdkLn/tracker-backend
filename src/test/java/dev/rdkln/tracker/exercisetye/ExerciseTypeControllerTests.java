package dev.rdkln.tracker.exercisetye;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.rdkln.tracker.exercisetype.ExerciseTypeController;
import dev.rdkln.tracker.exercisetype.domain.ExerciseType;
import dev.rdkln.tracker.exercisetype.domain.ExerciseTypeRepository;

@WebMvcTest(controllers = ExerciseTypeController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc
public class ExerciseTypeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ExerciseTypeRepository repository;

    List<ExerciseType> types = new ArrayList<>();

    @BeforeEach
    void setUp() {
        types = List.of(ExerciseType.builder().id(1L).build(), ExerciseType.builder().id(2L).build());
    }

    @Test
    void shouldListExerciseTypes() throws Exception {
        when(repository.findAll()).thenReturn(types);
        mockMvc.perform(get("/exercise-type")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }
}
