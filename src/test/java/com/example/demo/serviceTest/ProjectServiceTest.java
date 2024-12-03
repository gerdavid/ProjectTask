package com.example.demo.serviceTest;

import com.example.demo.dto.ProjectSummaryDTO;
import com.example.demo.entity.Project;
import com.example.demo.entity.TaskStatus;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProjectSummary() {

        Project project1 = new Project();
        Project project2 = new Project();

        when(projectRepository.findAll()).thenReturn(Arrays.asList(project1, project2));
        when(taskRepository.countByProjectIdAndStatus(1L, TaskStatus.TO_DO)).thenReturn(5L);
        when(taskRepository.countByProjectIdAndStatus(1L, TaskStatus.IN_PROGRESS)).thenReturn(2L);
        when(taskRepository.countByProjectIdAndStatus(1L, TaskStatus.DONE)).thenReturn(3L);

        when(taskRepository.countByProjectIdAndStatus(2L, TaskStatus.TO_DO)).thenReturn(0L);
        when(taskRepository.countByProjectIdAndStatus(2L, TaskStatus.IN_PROGRESS)).thenReturn(4L);
        when(taskRepository.countByProjectIdAndStatus(2L, TaskStatus.DONE)).thenReturn(1L);

        // Act
        List<ProjectSummaryDTO> summary = projectService.getProjectSummary();

        // Assert
        assertEquals(2, summary.size());
        assertEquals(5L, summary.get(0).getToDoCount());
        assertEquals(2L, summary.get(0).getInProgressCount());
        assertEquals(3L, summary.get(0).getDoneCount());

        assertEquals(0L, summary.get(1).getToDoCount());
        assertEquals(4L, summary.get(1).getInProgressCount());
        assertEquals(1L, summary.get(1).getDoneCount());
    }
}
