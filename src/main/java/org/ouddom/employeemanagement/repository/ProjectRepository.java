package org.ouddom.employeemanagement.repository;

import org.ouddom.employeemanagement.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    Project findByName(String name);
}
