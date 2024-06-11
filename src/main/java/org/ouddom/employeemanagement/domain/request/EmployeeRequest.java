package org.ouddom.employeemanagement.domain.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ouddom.employeemanagement.domain.enums.Position;
import org.ouddom.employeemanagement.domain.entity.Department;
import org.ouddom.employeemanagement.domain.entity.Employee;
import org.ouddom.employeemanagement.domain.entity.Project;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Position position;
    private Department department;
    private List<Project> projects;

    public Employee toEntity(Department department,List<Project> projects){
        return new Employee(null,this.firstName,this.lastName,this.email,this.position,department,projects);
    }

    public Employee toEntity(UUID id,Department department,List<Project> projects){
        return new Employee(id,this.firstName,this.lastName,this.email,this.position,department,projects);
    }
}
