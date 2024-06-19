package org.ouddom.employeemanagement.service;

import org.ouddom.employeemanagement.common.ApiResponse;
import org.ouddom.employeemanagement.common.PageResponse;
import org.ouddom.employeemanagement.domain.dto.EmployeeDTO;
import org.ouddom.employeemanagement.domain.request.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    ApiResponse<EmployeeDTO> create(EmployeeRequest employeeRequest);
    ApiResponse<List<EmployeeDTO>> getAll();
}
