package com.dev.issue_tracker.controller;

import com.dev.issue_tracker.dto.CreateProjectRequestDTO;
import com.dev.issue_tracker.dto.PageResponse;
import com.dev.issue_tracker.dto.ProjectDTO;
import com.dev.issue_tracker.mapper.ProjectMapper;
import com.dev.issue_tracker.model.Project;
import com.dev.issue_tracker.model.User;
import com.dev.issue_tracker.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

  private final ProjectService projectService;

  @GetMapping
  public ResponseEntity<PageResponse<ProjectDTO>> getAll(
      @RequestParam(defaultValue = "false") boolean mine,
      @AuthenticationPrincipal User user,
      @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
          Pageable pageable) {
    Page<Project> page = projectService.getProjects(mine, user, pageable);
    Page<ProjectDTO> dtoPage = page.map(ProjectMapper::toDTO);
    return ResponseEntity.ok(PageResponse.from(dtoPage));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProjectDTO> getById(@PathVariable Integer id) {
    return ResponseEntity.ok(ProjectMapper.toDTO(projectService.getProjectById(id)));
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ProjectDTO> create(
      @Valid @RequestBody CreateProjectRequestDTO request, @AuthenticationPrincipal User user) {
    Project project = projectService.createProject(ProjectMapper.fromCreateRequest(request), user);
    return ResponseEntity.ok(ProjectMapper.toDTO(project));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> delete(@PathVariable Integer id, @AuthenticationPrincipal User user) {
    projectService.deleteProject(id, user);
    return ResponseEntity.noContent().build();
  }
}
