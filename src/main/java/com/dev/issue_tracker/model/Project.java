package com.dev.issue_tracker.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  private String description;

  private Instant createdAt;

  @Builder.Default
  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Issue> issues = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "created_by")
  private User createdBy;
}
