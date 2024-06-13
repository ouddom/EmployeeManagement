package org.ouddom.employeemanagement.service.implementation;

import lombok.AllArgsConstructor;
import org.ouddom.employeemanagement.common.ApiResponse;
import org.ouddom.employeemanagement.common.PageResponse;
import org.ouddom.employeemanagement.domain.dto.DepartmentDTO;
import org.ouddom.employeemanagement.domain.dto.ProjectDTO;
import org.ouddom.employeemanagement.domain.entity.Department;
import org.ouddom.employeemanagement.domain.entity.Project;
import org.ouddom.employeemanagement.domain.request.DepartmentRequest;
import org.ouddom.employeemanagement.exception.NotFoundExceptionClass;
import org.ouddom.employeemanagement.repository.DepartmentRepository;
import org.ouddom.employeemanagement.service.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public ApiResponse<DepartmentDTO> create(DepartmentRequest departmentRequest) {
        return ApiResponse.<DepartmentDTO>builder()
                .message("Create Department Successfully")
                .payload(departmentRepository.save(departmentRequest.toEntity(departmentRequest.getName())).toDto())
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public PageResponse<List<DepartmentDTO>> getAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<DepartmentDTO> page = departmentRepository.findAll(pageable).map(Department::toDto);
        if(page.getTotalElements() == 0){
            throw new NotFoundExceptionClass("Department not found");
        }else{
            return PageResponse.<List<DepartmentDTO>>builder()
                    .message("Get all Departments successfully")
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
