package org.ouddom.employeemanagement.repository;

import org.ouddom.employeemanagement.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query("SELECT DISTINCT e FROM Employee e JOIN FETCH e.projects LEFT JOIN FETCH e.department")
    List<Employee> findAllWithProjectsAndDepartment();
}
