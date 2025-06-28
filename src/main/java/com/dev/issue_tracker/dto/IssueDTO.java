package com.dev.issue_tracker.dto;

import com.dev.issue_tracker.model.IssueStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueDTO {
    private UUID id;
    private String title;
    private String description;
    private IssueStatus status;
    private UUID projectId;
    private String createdBy;
}
