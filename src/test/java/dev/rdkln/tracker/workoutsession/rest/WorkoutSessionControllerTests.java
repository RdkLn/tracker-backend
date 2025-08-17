package dev.rdkln.tracker.workoutsession.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.rdkln.tracker.workoutsession.WorkoutService;

@WebMvcTest(controllers = WorkoutSessionController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc
public class WorkoutSessionControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    WorkoutService workoutService;
}
