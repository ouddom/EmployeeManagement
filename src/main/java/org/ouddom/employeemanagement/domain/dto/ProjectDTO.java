package org.ouddom.employeemanagement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ouddom.employeemanagement.domain.entity.Employee;


import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private UUID id;
    private String name;
}
