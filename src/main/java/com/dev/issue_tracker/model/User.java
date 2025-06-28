package com.dev.issue_tracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Issue> issues;

}