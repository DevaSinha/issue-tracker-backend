package com.dev.issue_tracker.repository;

import com.dev.issue_tracker.model.Project;
import com.dev.issue_tracker.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    List<Project> findByCreatedBy(User user);
    Page<Project> findByCreatedBy(User user, Pageable pageable);

}
