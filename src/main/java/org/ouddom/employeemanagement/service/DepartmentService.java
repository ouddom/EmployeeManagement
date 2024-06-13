package org.ouddom.employeemanagement.service;

import org.ouddom.employeemanagement.common.ApiResponse;
import org.ouddom.employeemanagement.common.PageResponse;
import org.ouddom.employeemanagement.domain.dto.DepartmentDTO;
import org.ouddom.employeemanagement.domain.request.DepartmentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    ApiResponse<DepartmentDTO> create(DepartmentRequest departmentRequest);
    PageResponse<List<DepartmentDTO>> getAll(Integer pageNo, Integer pageSize);
}
