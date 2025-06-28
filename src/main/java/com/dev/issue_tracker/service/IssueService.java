package com.dev.issue_tracker.service;

import com.dev.issue_tracker.exception.ForbiddenException;
import com.dev.issue_tracker.exception.NotFoundException;
import com.dev.issue_tracker.model.Issue;
import com.dev.issue_tracker.model.Project;
import com.dev.issue_tracker.model.User;
import com.dev.issue_tracker.repository.IssueRepository;
import com.dev.issue_tracker.repository.ProjectRepository;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueService {
  private final IssueRepository issueRepository;
  private final ProjectRepository projectRepository;

  public List<Issue> getIssuesByProjectId(UUID projectId) {
    return issueRepository.findAll().stream()
        .filter(issue -> issue.getProject().getId().equals(projectId))
        .toList();
  }

  public Issue createIssue(UUID projectId, Issue issue, User user) {
    Project project =
        projectRepository
            .findById(projectId)
            .orElseThrow(() -> new NotFoundException("Project not found"));
    issue.setProject(project);
    issue.setCreatedAt(Instant.now());
    issue.setUpdatedAt(Instant.now());
    issue.setCreatedBy(user);
    return issueRepository.save(issue);
  }

  private boolean userOwnsIssue(User user, Issue issue) {
    return issue.getCreatedBy() != null && issue.getCreatedBy().getId().equals(user.getId());
  }

  public void deleteIssue(UUID issueId, User user) {
    Issue issue =
        issueRepository
            .findById(issueId)
            .orElseThrow(() -> new NotFoundException("Issue not found"));

    if (!userOwnsIssue(user, issue)) {
      throw new ForbiddenException("You can't delete this issue");
    }

    issueRepository.delete(issue);
  }

  public Page<Issue> getIssuesByProject(
      UUID projectId, boolean mine, User user, Pageable pageable) {
    if (mine) {
      return issueRepository.findByProjectIdAndCreatedBy(projectId, user, pageable);
    }
    return issueRepository.findByProjectId(projectId, pageable);
  }
}
