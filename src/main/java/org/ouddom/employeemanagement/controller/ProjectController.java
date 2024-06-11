package org.ouddom.employeemanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.ouddom.employeemanagement.domain.request.ProjectRequest;
import org.ouddom.employeemanagement.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/projects")
@AllArgsConstructor

@SecurityRequirement(name = "bearerAuth")

@Tag(name = "Projects", description = "Operations related to employee management")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("")
    @Operation(summary = "Create a new project",description = "Create project to database")
    public ResponseEntity<?> create(@RequestBody ProjectRequest projectRequest){
        return ResponseEntity.ok().body(projectService.create(projectRequest));
    }

    @GetMapping("")
    @Operation(summary = "Get all projects",description = "Get all projects from database")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize){
        return ResponseEntity.ok().body(projectService.getAll(pageNo,pageSize));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete project by id",description = "Delete project from database")
    public ResponseEntity<?> deleteById(@PathVariable UUID id){
        return ResponseEntity.ok().body(projectService.deleteById(id));
    }

    @PutMapping("{id}")
    @Operation(summary = "Update project by id",description = "Update project to database")
    public ResponseEntity<?> updateById(@PathVariable UUID id, ProjectRequest projectRequest){
        return ResponseEntity.ok().body(projectService.updateById(id,projectRequest));
    }


}
