package pl.michalszymanski.microservices.projects.service;

import pl.michalszymanski.microservices.projects.api.dto.ProjectResource;

public interface ProjectInserter {

    ProjectResource insert(ProjectResource projectResource);
}
