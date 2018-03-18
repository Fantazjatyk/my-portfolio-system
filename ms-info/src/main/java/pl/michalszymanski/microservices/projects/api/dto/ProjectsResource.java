package pl.michalszymanski.microservices.projects.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectsResource {

    private List<ProjectResource> projects;

    public ProjectsResource(List<ProjectResource> projects) {
        this.projects = projects;
    }
}
