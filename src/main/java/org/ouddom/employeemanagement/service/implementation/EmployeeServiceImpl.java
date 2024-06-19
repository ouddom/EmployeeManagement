package org.ouddom.employeemanagement.service.implementation;

import lombok.AllArgsConstructor;
import org.ouddom.employeemanagement.common.ApiResponse;
import org.ouddom.employeemanagement.domain.dto.*;
import org.ouddom.employeemanagement.domain.entity.Department;
import org.ouddom.employeemanagement.domain.entity.Employee;
import org.ouddom.employeemanagement.domain.entity.Project;
import org.ouddom.employeemanagement.domain.request.EmployeeRequest;
import org.ouddom.employeemanagement.exception.NotFoundExceptionClass;
import org.ouddom.employeemanagement.exception.NullExceptionClass;
import org.ouddom.employeemanagement.repository.DepartmentRepository;
import org.ouddom.employeemanagement.repository.EmployeeRepository;
import org.ouddom.employeemanagement.repository.ProjectRepository;
import org.ouddom.employeemanagement.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final DepartmentRepository departmentRepository;
    @Override
    public ApiResponse<EmployeeDTO> create(EmployeeRequest employeeRequest) {

        if (employeeRequest.getDepartmentId() == null) {
            throw new NullExceptionClass("Department field is required!", "Department");
        }
        Department department = departmentRepository.findById(employeeRequest.getDepartmentId())
                .orElseThrow(() -> new NotFoundExceptionClass("Department not found!"));

        List<Project> projects = new ArrayList<>();
        if (employeeRequest.getProjectIds() != null) {
            projects = employeeRequest.getProjectIds().stream()
                    .map(projectId -> projectRepository.findById(projectId)
                            .orElseThrow(() -> new NotFoundExceptionClass("Project not found!")))
                    .collect(Collectors.toList());
        }

        Employee employee = employeeRequest.toEntity(employeeRequest.getName(), department, projects);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDTO employeeDTO = savedEmployee.toDto();

        return ApiResponse.<EmployeeDTO>builder()
                .message("Create Employee successfully!")
                .payload(employeeDTO)
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public ApiResponse<List<EmployeeDTO>> getAll() {
        List<Employee> employees = employeeRepository.findAllWithProjectsAndDepartment();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setName(employee.getName());

            DepartmentDTO departmentDto = new DepartmentDTO();
            departmentDto.setId(employee.getDepartment().getId());
            departmentDto.setName(employee.getDepartment().getName());


            List<ProjectEmployeeDto> projectEmployee = employee.getProjects().stream()
                    .map(project -> {
                        ProjectEmployeeDto projectEmployeeDto = new ProjectEmployeeDto();
                        projectEmployeeDto.setId(project.getId());
                        projectEmployeeDto.setName(project.getName());
                        return projectEmployeeDto;
                    })
                    .collect(Collectors.toList());

            employeeDTO.setDepartmentDTO(departmentDto);
            employeeDTO.setProjectsDTO(projectEmployee);
            employeeDTOS.add(employeeDTO);

        }
        return ApiResponse.<List<EmployeeDTO>>builder()
                .message("Get all employees successfully")
                .payload(employeeDTOS)
                .status(HttpStatus.OK)
                .build();
    }
}
