package com.dev.issue_tracker.mapper;

import com.dev.issue_tracker.dto.CreateProjectRequestDTO;
import com.dev.issue_tracker.dto.CreateProjectRequestDTO;
import com.dev.issue_tracker.dto.ProjectDTO;
import com.dev.issue_tracker.model.Project;
import jakarta.validation.Valid;

public class ProjectMapper {

    public static ProjectDTO toDTO(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .createdBy(project.getCreatedBy().getEmail())
                .build();
    }

    public static Project fromCreateRequest(@Valid CreateProjectRequestDTO request) {
        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        return project;
    }
}
