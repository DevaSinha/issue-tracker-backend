package com.dev.issue_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProjectRequestDTO {
    @NotBlank
    private String name;

    private String description;
}