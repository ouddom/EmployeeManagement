package org.ouddom.employeemanagement.service;

import org.ouddom.employeemanagement.domain.dto.ProjectDTO;
import org.ouddom.employeemanagement.domain.entity.Project;
import org.ouddom.employeemanagement.domain.request.ProjectRequest;
import org.ouddom.employeemanagement.common.ApiResponse;
import org.ouddom.employeemanagement.common.PageResponse;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    ApiResponse<ProjectDTO> create(ProjectRequest project);
    ApiResponse<List<ProjectDTO>> getAll();
    ApiResponse<ProjectDTO> deleteById(UUID id);
    ApiResponse<ProjectDTO> updateById(UUID id,ProjectRequest projectRequest);
}
