package pl.michalszymanski.microservices.projects.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michalszymanski.microservices.projects.entity.Project;
import pl.michalszymanski.microservices.projects.entity.ProjectRepository;
import pl.michalszymanski.microservices.projects.api.dto.ProjectResource;
import pl.michalszymanski.microservices.projects.mapping.ProjectConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectResolverImpl implements ProjectResolver {

    private ProjectRepository projectRepository;
    private ProjectConverter projectConverter;

    @Autowired
    public ProjectResolverImpl(ProjectRepository projectRepository, ProjectConverter projectConverter) {
        this.projectConverter = projectConverter;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectResource> findAll() {
        return projectRepository.findAll().stream().map(el -> projectConverter.toDto(el)).collect(Collectors.toList());
    }

    @Override
    public Optional<ProjectResource> findByName(String name) {
        Project project = projectRepository.findByName(name);

        if (project != null) {
            return Optional.of(projectConverter.toDto(project));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ProjectResource> findById(Long id) {
        Project project = projectRepository.findOne(id);

        if (project != null) {
            return Optional.of(projectConverter.toDto(project));
        }
        return Optional.empty();
    }
}
