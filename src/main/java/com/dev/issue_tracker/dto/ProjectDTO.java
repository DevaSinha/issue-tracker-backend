package com.dev.issue_tracker.dto;

import lombok.*;

import java.util.UUID;

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
