package org.ouddom.employeemanagement.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ouddom.employeemanagement.domain.entity.Employee;
import org.ouddom.employeemanagement.domain.entity.Project;

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
    public Project toEntity(UUID id,String name){
        return new Project(id,this.name);
    }
}
