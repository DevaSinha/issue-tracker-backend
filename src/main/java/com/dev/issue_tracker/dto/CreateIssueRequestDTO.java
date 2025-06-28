package com.dev.issue_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateIssueRequestDTO {
  @NotBlank private String title;

  private String description;
  private String status; // or enum
}
