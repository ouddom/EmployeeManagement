package org.ouddom.employeemanagement.service.implementation;

import lombok.AllArgsConstructor;
import org.ouddom.employeemanagement.common.ApiResponse;
import org.ouddom.employeemanagement.common.PageResponse;
import org.ouddom.employeemanagement.domain.dto.EmployeeDTO;
import org.ouddom.employeemanagement.domain.entity.Department;
import org.ouddom.employeemanagement.domain.entity.Employee;
import org.ouddom.employeemanagement.domain.entity.Project;
import org.ouddom.employeemanagement.domain.request.EmployeeRequest;
import org.ouddom.employeemanagement.domain.request.ProjectRequest;
import org.ouddom.employeemanagement.exception.NotFoundExceptionClass;
import org.ouddom.employeemanagement.exception.NullExceptionClass;
import org.ouddom.employeemanagement.repository.DepartmentRepository;
import org.ouddom.employeemanagement.repository.EmployeeRepository;
import org.ouddom.employeemanagement.repository.ProjectRepository;
import org.ouddom.employeemanagement.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final DepartmentRepository departmentRepository;
    @Override
    public ApiResponse<EmployeeDTO> create(EmployeeRequest employeeRequest) {
        List<Project> projects = new ArrayList<>();
        for(ProjectRequest projectRequest: employeeRequest.getProjects()){
            if(projectRequest.getName() == null || projectRequest.getName().isBlank()) {
                throw new NullExceptionClass("Project name can not be empty!","Project");
            }
            projects.add(projectRepository.findByName(employeeRequest.getName()));
        }

        if(employeeRequest.getDepartment() == null || employeeRequest.getDepartment().getName().isBlank()){
            throw new NullExceptionClass("Department name can not be empty!","Project");
        }
        Department department = departmentRepository.findByName(employeeRequest.getDepartment().getName());
        Employee employee = new Employee(null,employeeRequest.getName(),department,projects);
        employeeRepository.save(employee);
        return ApiResponse.<EmployeeDTO>builder()
                .message("Create Employee successfully!")
                .payload(null)
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public PageResponse<List<EmployeeDTO>> getAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<EmployeeDTO> page = employeeRepository.findAll(pageable).map(Employee::toDto);
        if(page.getTotalElements() == 0){
            throw new NotFoundExceptionClass("Employee not found");
        }else{
            return PageResponse.<List<EmployeeDTO>>builder()
                    .message("Get all employee successfully")
                    .payload(page.getContent())
                    .status(HttpStatus.OK)
                    .page(pageNo)
                    .size(pageSize)
                    .totalElement(page.getTotalElements())
                    .totalPages(page.getTotalPages())
                    .build();
        }
    }
}
