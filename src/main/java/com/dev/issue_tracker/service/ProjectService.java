package com.dev.issue_tracker.service;

import com.dev.issue_tracker.model.Project;
import com.dev.issue_tracker.model.User;
import com.dev.issue_tracker.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(UUID id) {
        return projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }

    public Project createProject(Project project, User user) {
        project.setCreatedAt(Instant.now());
        project.setCreatedBy(user);
        return projectRepository.save(project);
    }

    private boolean userOwnsProject(User user, Project project) {
        return project.getCreatedBy() != null &&
                project.getCreatedBy().getId().equals(user.getId());
    }

    public void deleteProject(UUID id, User user) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        if (!userOwnsProject(user, project)) {
            throw new AccessDeniedException("You can't delete this project");
        }

        projectRepository.delete(project);
    }

    public Page<Project> getProjects(boolean mine, User user, Pageable pageable) {
        if (mine) {
            return projectRepository.findByCreatedBy(user, pageable);
        }
        return projectRepository.findAll(pageable);
    }

}
