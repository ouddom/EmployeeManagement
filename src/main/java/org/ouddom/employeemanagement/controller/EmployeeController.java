package org.ouddom.employeemanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.ouddom.employeemanagement.domain.entity.Department;
import org.ouddom.employeemanagement.domain.entity.Project;
import org.ouddom.employeemanagement.domain.request.EmployeeRequest;
import org.ouddom.employeemanagement.domain.response.EmployeeResponse;
import org.ouddom.employeemanagement.repository.EmployeeRepository;
import org.ouddom.employeemanagement.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @PostMapping("")
    @Operation(summary = "Create a new employees",description = "Create employees to database")
    public ResponseEntity<?> create(@RequestBody EmployeeRequest employeeRequest){
        return ResponseEntity.ok().body(employeeService.create(employeeRequest));
    }

    @GetMapping("")
    @Operation(summary = "Get all employees",description = "Get all employees from database")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize){
        return ResponseEntity.ok().body(employeeRepository.findAll());
    }
}
