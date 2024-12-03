package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Column(nullable = false)
    private Date dueDate;

    @Column(name = "project_id", nullable = false) // Explicit column mapping
    private Long projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id", insertable = false, updatable = false) // Matches projectId column
    private Project project;
}
