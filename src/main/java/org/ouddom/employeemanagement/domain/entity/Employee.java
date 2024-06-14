package org.ouddom.employeemanagement.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.ouddom.employeemanagement.domain.dto.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department = new Department();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_projects",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "projects_id")
    )
    private List<Project> projects = new ArrayList<>();

    public EmployeeDTO toDto(){
        return new EmployeeDTO(
                this.id,
                this.name,
                this.department.toDto(),
                this.projects.stream().map(Project::toDto).toList()
        );
    }
}
