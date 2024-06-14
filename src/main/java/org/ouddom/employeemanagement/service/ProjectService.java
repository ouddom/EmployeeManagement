package org.ouddom.employeemanagement.service;

import org.ouddom.employeemanagement.domain.dto.ProjectDTO;
import org.ouddom.employeemanagement.domain.request.ProjectRequest;
import org.ouddom.employeemanagement.common.ApiResponse;
import org.ouddom.employeemanagement.common.PageResponse;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    ApiResponse<ProjectDTO> create(ProjectRequest project);
    PageResponse<List<ProjectDTO>> getAll(Integer pageNo, Integer pageSize);
    ApiResponse<ProjectDTO> deleteById(UUID id);
    ApiResponse<ProjectDTO> updateById(UUID id,ProjectRequest projectRequest);


}
