package com.dev.issue_tracker.dto;

import com.dev.issue_tracker.model.IssuePriority;
import com.dev.issue_tracker.model.IssueStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueDTO {
  private Integer id;
  private String title;
  private String description;
  private IssueStatus status;
  private IssuePriority priority;
  private Integer projectId;
  private String assignee;
  private String createdBy;
}
