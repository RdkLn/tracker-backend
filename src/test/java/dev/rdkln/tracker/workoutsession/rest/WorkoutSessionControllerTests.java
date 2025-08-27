package dev.rdkln.tracker.workoutsession.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.rdkln.tracker.user.domain.UserId;
import dev.rdkln.tracker.workoutsession.WorkoutService;

@WebMvcTest(controllers = WorkoutSessionController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc
public class WorkoutSessionControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    WorkoutService workoutService;

    @Test
    void shouldReturnDaysWhenWorkedOut() throws Exception{
        when(workoutService.listDaysWorkedOut(new UserId(1L)))
        .thenReturn(List.of(LocalDate.of(2025, 01, 01),LocalDate.of(2025, 01, 02),LocalDate.of(2025, 01, 03)));
        mockMvc.perform(get("/workouts/days-trained").param("userId", "1")).andExpect(status().isOk());
    }
}
