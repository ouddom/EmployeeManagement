package org.ouddom.employeemanagement.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ouddom.employeemanagement.domain.entity.Employee;
import org.ouddom.employeemanagement.domain.entity.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
    private String name;
    private List<UUID> employeeRequests = new ArrayList<>();
    public Project toEntity(String name, List<Employee> employees){
        return new Project(null,name,employees);
    }
    public Project toEntity(UUID id,String name,List<Employee> employees){
        return new Project(id,name,employees);
    }
}
