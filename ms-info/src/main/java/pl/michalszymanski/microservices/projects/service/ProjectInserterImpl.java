package pl.michalszymanski.microservices.projects.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michalszymanski.microservices.projects.api.dto.ProjectResource;
import pl.michalszymanski.microservices.projects.entity.Project;
import pl.michalszymanski.microservices.projects.entity.ProjectRepository;
import pl.michalszymanski.microservices.projects.mapping.ProjectConverter;

@Service
public class ProjectInserterImpl implements ProjectInserter {
    private ProjectRepository projectRepository;
    private ProjectConverter projectConverter;

    @Autowired
    public ProjectInserterImpl(ProjectRepository projectRepository, ProjectConverter projectConverter) {
        this.projectRepository = projectRepository;
        this.projectConverter = projectConverter;
    }

    @Override
    public ProjectResource insert(ProjectResource projectResource) {
        Project project = projectConverter.toEntity(projectResource);
        Project result = projectRepository.save(project);
        return projectConverter.toDto(result);
    }
}
