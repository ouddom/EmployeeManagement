package org.ouddom.employeemanagement.domain.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ouddom.employeemanagement.domain.enums.Position;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private UUID id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private DepartmentDTO departmentDTO;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ProjectDTO> projects;

}
