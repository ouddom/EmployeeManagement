package org.ouddom.employeemanagement.domain.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ouddom.employeemanagement.domain.entity.Department;
import org.ouddom.employeemanagement.domain.entity.Employee;
import org.ouddom.employeemanagement.domain.entity.Project;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private String name;
    private DepartmentRequest department;
    private List<ProjectRequest> projects;

    public Employee toEntity(Department department,List<Project> projects){
        return new Employee(null,this.name,department,projects);
    }

    public Employee toEntity(UUID id,Department department,List<Project> projects){
        return new Employee(id,this.name,department,projects);
    }
}
