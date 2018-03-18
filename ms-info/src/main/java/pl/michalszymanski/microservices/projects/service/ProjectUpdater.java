package pl.michalszymanski.microservices.projects.service;

import pl.michalszymanski.microservices.projects.api.dto.ProjectResource;

public interface ProjectUpdater {

    ProjectResource update(Long id, ProjectResource newOne);
}
