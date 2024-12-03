package com.example.demo.service;

import com.example.demo.dto.ProjectSummaryDTO;
import com.example.demo.entity.Project;
import com.example.demo.entity.TaskStatus;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    private TaskRepository taskRepository;
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long projectId) {
        return projectRepository.findById(projectId);
    }
    public List<ProjectSummaryDTO> getProjectSummary() {
        List<Project> projects = projectRepository.findAll();

        return projects.stream().map(project -> {
            Long toDoCount = taskRepository.countByProjectIdAndStatus(project.getId(), TaskStatus.TO_DO);
            Long inProgressCount = taskRepository.countByProjectIdAndStatus(project.getId(), TaskStatus.IN_PROGRESS);
            Long doneCount = taskRepository.countByProjectIdAndStatus(project.getId(), TaskStatus.DONE);

            return new ProjectSummaryDTO(
                    project.getId(),
                    project.getName(),
                    toDoCount,
                    inProgressCount,
                    doneCount
            );
        }).collect(Collectors.toList());
    }

}