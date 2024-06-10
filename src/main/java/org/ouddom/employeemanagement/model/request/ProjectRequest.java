package org.ouddom.employeemanagement.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ouddom.employeemanagement.model.dto.EmployeeDTO;
import org.ouddom.employeemanagement.model.entity.Employee;
import org.ouddom.employeemanagement.model.entity.Project;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
    private String name;
    public Project toEntity(String name){
        return new Project(null,this.name);
    }
}
