package com.dev.issue_tracker.repository;

import com.dev.issue_tracker.model.Issue;
import com.dev.issue_tracker.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IssueRepository extends JpaRepository<Issue, UUID> {
    List<Issue> findByProjectId(UUID projectId);
    List<Issue> findByProjectIdAndCreatedBy(UUID projectId, User user);
    Page<Issue> findByProjectId(UUID projectId, Pageable pageable);
    Page<Issue> findByProjectIdAndCreatedBy(UUID projectId, User user, Pageable pageable);

}
