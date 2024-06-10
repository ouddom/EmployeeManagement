package org.ouddom.employeemanagement.service;

import org.ouddom.employeemanagement.model.dto.ProjectDTO;
import org.ouddom.employeemanagement.model.request.EmployeeRequest;
import org.ouddom.employeemanagement.model.request.ProjectRequest;
import org.ouddom.employeemanagement.model.response.ApiResponse;
import org.ouddom.employeemanagement.model.response.PageResponse;

import java.util.List;

public interface ProjectService {
    ApiResponse<ProjectDTO> create(ProjectRequest project);
    PageResponse<List<ProjectDTO>> getAll(Integer pageNo, Integer pageSize);
}
