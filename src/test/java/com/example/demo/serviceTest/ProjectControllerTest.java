package com.example.demo.serviceTest;

import com.example.demo.controller.ProjectController;
import com.example.demo.dto.ProjectSummaryDTO;
import com.example.demo.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProjectControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ProjectController projectController;

    @Mock
    private ProjectService projectService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    @Test
    public void testGetProjectsSummary() throws Exception {

        ProjectSummaryDTO projectSummary1 = new ProjectSummaryDTO(1L, "Project 1", 5L, 2L, 3L);
        ProjectSummaryDTO projectSummary2 = new ProjectSummaryDTO(2L, "Project 2", 0L, 4L, 1L);

        when(projectService.getProjectSummary()).thenReturn(Arrays.asList(projectSummary1, projectSummary2));

        mockMvc.perform(get("/projects/summary"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].projectId").value(1))
                .andExpect(jsonPath("$[0].projectName").value("Project 1"))
                .andExpect(jsonPath("$[0].toDoCount").value(5))
                .andExpect(jsonPath("$[0].inProgressCount").value(2))
                .andExpect(jsonPath("$[0].doneCount").value(3))
                .andExpect(jsonPath("$[1].projectId").value(2))
                .andExpect(jsonPath("$[1].projectName").value("Project 2"))
                .andExpect(jsonPath("$[1].toDoCount").value(0))
                .andExpect(jsonPath("$[1].inProgressCount").value(4))
                .andExpect(jsonPath("$[1].doneCount").value(1));
    }
}

