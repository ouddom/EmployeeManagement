package org.ouddom.employeemanagement.repository;

import org.ouddom.employeemanagement.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    @Query("SELECT DISTINCT p FROM Project p LEFT JOIN FETCH p.employees")
    List<Project> findAllWithEmployees();
}
