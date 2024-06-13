package org.ouddom.employeemanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.ouddom.employeemanagement.domain.request.EmployeeRequest;
import org.ouddom.employeemanagement.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("")
    @Operation(summary = "Create a new department",description = "Create department to database")
    public ResponseEntity<?> create(@RequestBody EmployeeRequest employeeRequest){
        return ResponseEntity.ok().body(employeeService.create(employeeRequest));
    }

    @GetMapping("")
    @Operation(summary = "Get all departments",description = "Get all departments from database")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize){
        return ResponseEntity.ok().body(employeeService.getAll(pageNo,pageSize));
    }
}
