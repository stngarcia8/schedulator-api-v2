package cl.schedulator.api.infrastructure.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Task controller should")
class TaskControllerTest {

    @Autowired
    TaskController taskController;
    MockMvc mockMvc;


    @BeforeEach
    void setUp () {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController)
                .build();
    }


    @Test
    @DisplayName("Get task when microservice is ok and return a valid tasks list")
    public void getTask_whenMicroserviceIsOk_receivedValidTaskList () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1.0/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    @DisplayName("Get task ordered by day task quantity when microservice is ok and return a valid tasks list")
    public void getDayTaskQuantity_whenMicroserviceIsOk_receivedValidTaskList () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1.0/tasks/day")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}