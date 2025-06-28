package com.dev.issue_tracker.mapper;

import com.dev.issue_tracker.dto.CreateIssueRequestDTO;
import com.dev.issue_tracker.dto.IssueDTO;
import com.dev.issue_tracker.model.Issue;
import com.dev.issue_tracker.model.IssueStatus;

public class IssueMapper {
  public static IssueDTO toDTO(Issue issue) {
    return IssueDTO.builder()
        .id(issue.getId())
        .title(issue.getTitle())
        .description(issue.getDescription())
        .status(issue.getStatus())
        .projectId(issue.getProject().getId())
        .build();
  }

  public static Issue fromCreateRequest(CreateIssueRequestDTO request) {
    Issue issue = new Issue();
    issue.setTitle(request.getTitle());
    issue.setDescription(request.getDescription());
    issue.setStatus(IssueStatus.valueOf(request.getStatus()));
    return issue;
  }
}
