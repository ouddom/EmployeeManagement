package org.ouddom.employeemanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.ouddom.employeemanagement.domain.request.DepartmentRequest;
import org.ouddom.employeemanagement.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/departments")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping("")
    @Operation(summary = "Create a new department",description = "Create department to database")
    public ResponseEntity<?> create(@RequestBody DepartmentRequest departmentRequest){
        return ResponseEntity.ok().body(departmentService.create(departmentRequest));
    }

    @GetMapping("")
    @Operation(summary = "Get all departments",description = "Get all departments from database")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize){
        return ResponseEntity.ok().body(departmentService.getAll(pageNo,pageSize));
    }
}
