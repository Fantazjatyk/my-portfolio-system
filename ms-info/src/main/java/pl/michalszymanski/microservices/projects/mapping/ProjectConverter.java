package pl.michalszymanski.microservices.projects.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michalszymanski.microservices.projects.entity.Project;
import pl.michalszymanski.microservices.projects.api.dto.ProjectResource;

import java.util.stream.Collectors;

@Service
public class ProjectConverter implements DtoEntityConverter<ProjectResource, Project> {

    private MediaConverter mediaConverter;

    @Autowired
    public ProjectConverter(MediaConverter mediaConverter) {
        this.mediaConverter = mediaConverter;
    }

    @Override
    public ProjectResource toDto(Project entity) {
        ProjectResource projectResourceResponse = new ProjectResource();
        projectResourceResponse.setName(entity.getName());
        projectResourceResponse.setId(entity.getId());
        projectResourceResponse.setDescription(entity.getDescription());
        projectResourceResponse.setCreationDate((entity.getCreationDate()));
        projectResourceResponse.setMedias(entity.getMedias().stream().map(el -> mediaConverter.toDto(el)).collect(Collectors.toList()));
        return projectResourceResponse;
    }

    @Override
    public Project toEntity(ProjectResource dto) {
        Project project = new Project();
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setCreationDate(dto.getCreationDate());
        project.setMedias(dto.getMedias().stream().map(el -> mediaConverter.toEntity(el)).peek(el-> el.setProject(project)).collect(Collectors.toList()));
        return project;
    }
}
