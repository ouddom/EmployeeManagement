package org.ouddom.employeemanagement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeProjectDto {
    private UUID id;
    private String name;
}
