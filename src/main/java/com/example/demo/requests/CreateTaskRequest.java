package com.example.demo.requests;

import com.example.demo.entity.Project;
import lombok.Data;

import java.util.Date;

@Data
public class CreateTaskRequest {
    public Project projectId;
    private String title;
    private String description;
    private String status;
    private Date dueDate;

}
