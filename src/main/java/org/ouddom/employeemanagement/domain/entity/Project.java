package org.ouddom.employeemanagement.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ouddom.employeemanagement.domain.dto.ProjectDTO;

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
    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees;

    public Project(UUID id,String name){
        this.id = id;
        this.name = name;
    }
    public ProjectDTO toDto(){
        return new ProjectDTO(this.id,this.name);
    }
}
