package org.ouddom.employeemanagement.service.implementation;
import lombok.AllArgsConstructor;
import org.ouddom.employeemanagement.domain.entity.Employee;
import org.ouddom.employeemanagement.domain.entity.User;
import org.ouddom.employeemanagement.domain.request.EmployeeRequest;
import org.ouddom.employeemanagement.exception.NotFoundExceptionClass;
import org.ouddom.employeemanagement.exception.NullExceptionClass;
import org.ouddom.employeemanagement.domain.dto.ProjectDTO;
import org.ouddom.employeemanagement.domain.entity.Project;
import org.ouddom.employeemanagement.domain.request.ProjectRequest;
import org.ouddom.employeemanagement.common.ApiResponse;
import org.ouddom.employeemanagement.common.PageResponse;
import org.ouddom.employeemanagement.repository.EmployeeRepository;
import org.ouddom.employeemanagement.repository.ProjectRepository;
import org.ouddom.employeemanagement.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public ApiResponse<ProjectDTO> create(ProjectRequest projectRequest) {
        if(projectRequest.getName().isBlank()){
            throw new NullExceptionClass("A project name field is required","project");
        }else{
            return ApiResponse.<ProjectDTO>builder()
                    .message("Successfully create project")
                    .payload(projectRepository.save(projectRequest.toEntity(projectRequest.getName())).toDto())
                    .status(HttpStatus.CREATED)
                    .build();
        }
    }

    @Override
    public PageResponse<List<ProjectDTO>> getAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<ProjectDTO> page = projectRepository.findAll(pageable).map(Project::toDto);
        if(page.getTotalElements() == 0){
            throw new NotFoundExceptionClass("Project not found");
        }else{
            return PageResponse.<List<ProjectDTO>>builder()
                    .message("Get all projects successfully")
                    .payload(page.getContent())
                    .status(HttpStatus.OK)
                    .page(pageNo)
                    .size(pageSize)
                    .totalElement(page.getTotalElements())
                    .totalPages(page.getTotalPages())
                    .build();
        }
    }

    @Override
    public ApiResponse<ProjectDTO> deleteById(UUID id) {
        projectRepository.findById(id).orElseThrow(()-> new NotFoundExceptionClass("A project with id: " + id + " not exist!"));
        projectRepository.deleteById(id);
        return ApiResponse.<ProjectDTO>builder()
                .message("Delete project with id: " + id + " successfully")
                .payload(null)
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public ApiResponse<ProjectDTO> updateById(UUID id,ProjectRequest projectRequest) {
        if(projectRequest.getName().isBlank()){
            throw new NullExceptionClass("A project name field is required!","project");
        }else {
            projectRepository.findById(id).orElseThrow(() -> new NotFoundExceptionClass("A project with id: " + id + " not exist!"));
            return ApiResponse.<ProjectDTO>builder()
                    .message("Update project successfully")
                    .payload(null)
                    .status(HttpStatus.OK)
                    .build();

        }
    }
}
