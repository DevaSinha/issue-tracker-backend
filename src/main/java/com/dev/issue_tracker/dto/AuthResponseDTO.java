package com.dev.issue_tracker.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {
    private String token;
}