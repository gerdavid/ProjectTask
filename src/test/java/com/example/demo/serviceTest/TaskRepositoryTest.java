package com.example.demo.serviceTest;

import com.example.demo.entity.TaskStatus;
import com.example.demo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaskRepositoryTest {

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testCountTasksByProjectIdAndStatus() {

        Long projectId = 1L;
        TaskStatus status = TaskStatus.TO_DO;
        when(taskRepository.countByProjectIdAndStatus(projectId, status)).thenReturn(5L);


        Long taskCount = taskRepository.countByProjectIdAndStatus(projectId, status);


        assertEquals(5L, taskCount);
        verify(taskRepository, times(1)).countByProjectIdAndStatus(projectId, status);
    }
}
