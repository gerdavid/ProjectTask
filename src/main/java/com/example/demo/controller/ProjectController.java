package com.example.demo.controller;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.dto.ProjectSummaryDTO;
import com.example.demo.entity.Project;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.requests.CreateProjectRequest;
import com.example.demo.requests.UpdateProjectRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody CreateProjectRequest request) {
        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        return ResponseEntity.ok(ProjectMapper.toDTO(projectService.createProject(project)));
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.ok(
                projectService.getAllProjects().stream()
                        .map(ProjectMapper::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long projectId) {
        return projectService.getProjectById(projectId)
                .map(project -> ResponseEntity.ok(ProjectMapper.toDTO(project)))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/projects/summary")
    public List<ProjectSummaryDTO> getProjectsSummary() {
        return projectService.getProjectSummary();
    }

}
