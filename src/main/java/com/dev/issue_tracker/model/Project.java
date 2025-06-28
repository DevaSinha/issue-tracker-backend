package com.dev.issue_tracker.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @UuidGenerator
    private UUID id;

    private String name;
    private String description;

    private Instant createdAt;

    @Builder.Default
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Issue> issues = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

}
