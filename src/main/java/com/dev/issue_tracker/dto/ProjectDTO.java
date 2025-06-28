package com.dev.issue_tracker.dto;

import java.util.UUID;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {
  private UUID id;
  private String name;
  private String description;
  private String createdBy;
}
