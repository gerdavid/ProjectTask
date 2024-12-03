package com.example.demo.controller;

import com.example.demo.dto.TaskDTO;
import com.example.demo.entity.TaskStatus;
import com.example.demo.mapper.TaskMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.TaskService;
import com.example.demo.entity.Task;
import com.example.demo.requests.CreateTaskRequest;
import com.example.demo.requests.UpdateTaskRequest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(
            @PathVariable Long projectId, @RequestBody CreateTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(TaskStatus.valueOf(request.getStatus().toUpperCase()));
        task.setDueDate(request.getDueDate());
        task.setProjectId(projectId);

        return ResponseEntity.ok(TaskMapper.toDTO(taskService.createTask(task)));
    }


    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks(@PathVariable Long projectId) {
        return ResponseEntity.ok(
                taskService.getTasksByProjectId(projectId).stream()
                        .map(TaskMapper::toDTO)
                        .collect(Collectors.toList())
        );
    }
    @GetMapping("/byStatusAndDueDate")
    public ResponseEntity<List<TaskDTO>> getTasks(
            @PathVariable Long projectId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Date dueDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        TaskStatus taskStatus = (status != null) ? TaskStatus.valueOf(status.toUpperCase()) : null;
        Pageable pageable = PageRequest.of(page, size);
        Page<Task> tasksPage = taskService.getTasksByProjectIdAndFilters(projectId, taskStatus, dueDate, pageable);

        return ResponseEntity.ok(
                tasksPage.stream()
                        .map(TaskMapper::toDTO)
                        .collect(Collectors.toList())
        );
    }
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(
            @PathVariable Long taskId, @RequestBody UpdateTaskRequest request) {
        Task updatedTask = new Task();
        updatedTask.setTitle(request.getTitle());
        updatedTask.setDescription(request.getDescription());
        updatedTask.setStatus(TaskStatus.valueOf(request.getStatus().toUpperCase()));
        updatedTask.setDueDate(request.getDueDate());
        return ResponseEntity.ok(TaskMapper.toDTO(taskService.updateTask(taskId, updatedTask)));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
