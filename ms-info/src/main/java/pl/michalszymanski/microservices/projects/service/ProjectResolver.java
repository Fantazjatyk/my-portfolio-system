package pl.michalszymanski.microservices.projects.service;

import pl.michalszymanski.microservices.projects.api.dto.ProjectResource;

import java.util.List;
import java.util.Optional;

public interface ProjectResolver {

    List<ProjectResource> findAll();

    Optional<ProjectResource> findByName(String name);

    Optional<ProjectResource> findById(Long id);
}
