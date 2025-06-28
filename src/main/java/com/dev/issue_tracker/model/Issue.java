package com.dev.issue_tracker.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Issue {

  @Id @UuidGenerator private UUID id;

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
}
