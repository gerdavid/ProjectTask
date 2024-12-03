package com.example.demo.dto;

import lombok.Data;

@Data
public class ProjectSummaryDTO {
    private Long projectId;
    private String projectName;
    private Long toDoCount;
    private Long inProgressCount;
    private Long doneCount;


    public ProjectSummaryDTO() {
    }


    public ProjectSummaryDTO(Long projectId, String projectName, Long toDoCount, Long inProgressCount, Long doneCount) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.toDoCount = toDoCount;
        this.inProgressCount = inProgressCount;
        this.doneCount = doneCount;
    }
}
