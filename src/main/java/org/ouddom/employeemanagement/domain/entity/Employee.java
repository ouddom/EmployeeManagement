package org.ouddom.employeemanagement.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ouddom.employeemanagement.domain.enums.Position;
import org.ouddom.employeemanagement.domain.dto.EmployeeDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Position position;

    @ManyToOne
    @JoinColumn(name = "department_id",nullable = false)
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

    public EmployeeDTO toDto(){
        return new EmployeeDTO(
                this.id,
                this.firstName,
                this.lastName,
                this.email,
                this.position,
                this.department.toDto(),
                this.projects.stream().map(Project::toDto).collect(Collectors.toList())
        );
    }
}
