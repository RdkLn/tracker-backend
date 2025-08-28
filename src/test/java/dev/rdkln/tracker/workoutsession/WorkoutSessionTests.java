package dev.rdkln.tracker.workoutsession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import dev.rdkln.tracker.config.CustomTestConfiguration;
import dev.rdkln.tracker.user.domain.UserId;
import dev.rdkln.tracker.workoutsession.domain.WorkoutSession;
import dev.rdkln.tracker.workoutsession.domain.WorkoutSessionRepository;

@SpringBootTest
@Import(CustomTestConfiguration.class)
@AutoConfigureMockMvc
public class WorkoutSessionTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private PostgreSQLContainer container;

    @Autowired
    private WorkoutSessionRepository repository;

    @BeforeEach
    void setUp() {
        repository.save(
                WorkoutSession.builder().date(LocalDateTime.of(2025, 12, 12, 0, 0, 0)).userId(new UserId(1L)).build());
    }

    @AfterEach
    void cleanUp() {
        repository.deleteAll();
    }

    @Test
    void shouldReturnSessionByDate() throws Exception {
        Long userId = 1L;
        LocalDate date = LocalDate.of(2025, 12, 12);
        mockMvc.perform(get("/workouts").param("date", date.toString()).param("userId", userId.toString()))
                .andExpect(status().isOk());
    }
    @Test
    void shouldThrowWorkoutSessionNotFoundException() throws Exception {
        Long userId = 1L;
        LocalDate invalidDate = LocalDate.of(2021, 12, 12);
        mockMvc.perform(get("/workouts").param("date", invalidDate.toString()).param("userId", userId.toString())).andExpect(status().isNotFound()).andExpect(jsonPath("$.title").value("Not found error"));
    }
}
