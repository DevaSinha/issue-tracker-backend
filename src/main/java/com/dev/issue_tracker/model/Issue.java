package com.dev.issue_tracker.model;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String title;
  private String description;

  @Enumerated(EnumType.STRING)
  private IssueStatus status;

  private Instant createdAt;
  private Instant updatedAt;

  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;

  @ManyToOne
  @JoinColumn(name = "created_by")
  private User createdBy;

  @Enumerated(EnumType.STRING)
  private IssuePriority priority;

  @ManyToOne
  @JoinColumn(name = "assignee_id")
  private User assignee;
}
