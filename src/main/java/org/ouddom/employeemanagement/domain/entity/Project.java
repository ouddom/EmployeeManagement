package org.ouddom.employeemanagement.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ouddom.employeemanagement.domain.dto.ProjectDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projects")
    private List<Employee> employees = new ArrayList<>();

    public ProjectDTO toDto(){
        return new ProjectDTO(this.id,this.name);
    }
}
