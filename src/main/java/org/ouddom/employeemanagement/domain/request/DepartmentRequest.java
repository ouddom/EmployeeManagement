package org.ouddom.employeemanagement.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ouddom.employeemanagement.domain.dto.EmployeeDTO;
import org.ouddom.employeemanagement.domain.entity.Department;
import org.ouddom.employeemanagement.domain.entity.Employee;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {
    private String name;
    private List<EmployeeDTO> employees;

    public Department toEntity(List<Employee> employees){
        return new Department(null,this.name,employees);
    }

    public Department toEntity(UUID id,List<Employee> employees){
        return new Department(id,this.name,employees);
    }
}
