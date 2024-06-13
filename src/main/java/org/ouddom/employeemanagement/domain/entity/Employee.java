package org.ouddom.employeemanagement.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ouddom.employeemanagement.domain.dto.ProjectDTO;
import org.ouddom.employeemanagement.domain.enums.Position;
import org.ouddom.employeemanagement.domain.dto.EmployeeDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Data
@Entity
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_projects",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "projects_id")
    )
    private List<Project> projects;

    public Employee(UUID id, String name , Department department , List<Project> projects){
        this.id = id;
        this.name = name;
        this.department = department;
        this.projects = projects;
    }

    public EmployeeDTO toDto(){
        return new EmployeeDTO(
                this.id,
                this.name,
                this.department.toDto(),
                this.projects.stream().map(Project::toDto).collect(Collectors.toList())
        );
    }
}
