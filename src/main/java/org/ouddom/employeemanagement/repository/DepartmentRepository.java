package org.ouddom.employeemanagement.repository;

import org.ouddom.employeemanagement.domain.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    Department findByName(String name);

}
