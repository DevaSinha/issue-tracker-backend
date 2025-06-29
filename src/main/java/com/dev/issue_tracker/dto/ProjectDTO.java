package com.dev.issue_tracker.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {
  private Integer id;
  private String name;
  private String description;
  private String createdBy;
}
