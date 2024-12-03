package com.example.demo.mapper;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.entity.Project;

import java.util.stream.Collectors;

public class ProjectMapper {
    public static ProjectDTO toDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setTasks(project.getTasks().stream()
                .map(TaskMapper::toDTO)
                .collect(Collectors.toList()));
        return dto;
    }
}