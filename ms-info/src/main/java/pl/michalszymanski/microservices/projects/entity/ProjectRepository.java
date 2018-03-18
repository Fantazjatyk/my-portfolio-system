package pl.michalszymanski.microservices.projects.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByName(String name);
}
