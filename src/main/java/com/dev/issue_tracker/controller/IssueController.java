package com.dev.issue_tracker.controller;

import com.dev.issue_tracker.dto.IssueDTO;
import com.dev.issue_tracker.dto.PageResponse;
import com.dev.issue_tracker.mapper.IssueMapper;
import com.dev.issue_tracker.model.Issue;
import com.dev.issue_tracker.model.User;
import com.dev.issue_tracker.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects/{projectId}/issues")
@RequiredArgsConstructor
public class IssueController {
    private final IssueService issueService;

    @GetMapping
    public ResponseEntity<PageResponse<IssueDTO>> getAll(
            @PathVariable UUID projectId,
            @RequestParam(defaultValue = "false") boolean mine,
            @AuthenticationPrincipal User user,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Issue> page = issueService.getIssuesByProject(projectId, mine, user, pageable);
        Page<IssueDTO> dtoPage = page.map(IssueMapper::toDTO);
        return ResponseEntity.ok(PageResponse.from(dtoPage));
    }


    @PostMapping
    public IssueDTO create(@PathVariable UUID projectId, @RequestBody Issue issue, @AuthenticationPrincipal User user) {
        return IssueMapper.toDTO(issueService.createIssue(projectId, issue, user));
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<Void> delete(@PathVariable UUID issueId,
                                       @AuthenticationPrincipal User user) {
        issueService.deleteIssue(issueId, user);
        return ResponseEntity.noContent().build();
    }

}
