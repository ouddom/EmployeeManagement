package org.ouddom.employeemanagement.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ouddom.employeemanagement.model.dto.DepartmentDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public DepartmentDTO toDto(){
        return new DepartmentDTO(this.id,this.name);
    }
}
