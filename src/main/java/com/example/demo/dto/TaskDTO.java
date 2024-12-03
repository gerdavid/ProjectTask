package com.example.demo.dto;

import com.example.demo.entity.TaskStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Date dueDate;
}
