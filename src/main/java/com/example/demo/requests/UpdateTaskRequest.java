package com.example.demo.requests;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateTaskRequest {
    private String title;
    private String description;
    private String status; // Will be converted to TaskStatus enum
    private Date dueDate;
}
