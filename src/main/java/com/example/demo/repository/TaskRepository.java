package com.example.demo.repository;

import com.example.demo.entity.Task;
import com.example.demo.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);
    @Query("SELECT t FROM Task t WHERE t.projectId = :projectId " +
            "AND (:status IS NULL OR t.status = :status) " +
            "AND (:dueDate IS NULL OR t.dueDate = :dueDate)")
    Page<Task> findByProjectIdAndFilters(
            @Param("projectId") Long projectId,
            @Param("status") TaskStatus status,
            @Param("dueDate") Date dueDate,
            Pageable pageable
    );
    Long countByProjectIdAndStatus(Long projectId, TaskStatus status);
}
