package org.ouddom.employeemanagement.service.implementation;
import lombok.AllArgsConstructor;
import org.ouddom.employeemanagement.exception.NotFoundExceptionClass;
import org.ouddom.employeemanagement.exception.NullExceptionClass;
import org.ouddom.employeemanagement.model.dto.ProjectDTO;
import org.ouddom.employeemanagement.model.entity.Project;
import org.ouddom.employeemanagement.model.request.ProjectRequest;
import org.ouddom.employeemanagement.model.response.ApiResponse;
import org.ouddom.employeemanagement.model.response.PageResponse;
import org.ouddom.employeemanagement.repository.ProjectRepository;
import org.ouddom.employeemanagement.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

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
}
