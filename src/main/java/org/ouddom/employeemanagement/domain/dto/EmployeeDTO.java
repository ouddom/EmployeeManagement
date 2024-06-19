package org.ouddom.employeemanagement.domain.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private UUID id;
    private String name;
    private DepartmentDTO departmentDTO;
    private List<ProjectEmployeeDto> projectsDTO;

}
