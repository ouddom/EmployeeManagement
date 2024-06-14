package org.ouddom.employeemanagement.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private String name;
    private String department;
    private List<String> projects;
}
