package com.dev.issue_tracker.repository;

import com.dev.issue_tracker.model.Issue;
import com.dev.issue_tracker.model.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
  List<Issue> findByProjectId(Integer projectId);

  List<Issue> findByProjectIdAndCreatedBy(Integer projectId, User user);

  Page<Issue> findByProjectId(Integer projectId, Pageable pageable);

  Page<Issue> findByProjectIdAndCreatedBy(Integer projectId, User user, Pageable pageable);
}
