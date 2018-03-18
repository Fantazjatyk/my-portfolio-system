package pl.michalszymanski.microservices.projects.service

import pl.michalszymanski.microservices.projects.entity.Project
import pl.michalszymanski.microservices.projects.entity.ProjectRepository
import pl.michalszymanski.microservices.projects.api.dto.ProjectResource
import pl.michalszymanski.microservices.projects.mapping.ProjectConverter
import static org.assertj.core.api.Assertions.assertThat;
import spock.lang.Specification

class ProjectResolverImplTest extends Specification {

    private ProjectResolverImpl projectResolverImpl
    private ProjectConverter projectConverter
    private ProjectRepository projectRepository;

    def setup() {
        this.projectConverter = Stub(ProjectConverter)
        this.projectRepository = Stub(ProjectRepository)
        this.projectResolverImpl = new ProjectResolverImpl(projectRepository, projectConverter)
    }

    def "should find all projects"() {

        given:
        Project projectA = new Project()
        projectA.name = "a"
        ProjectResource projectAResource = new ProjectResource()
        projectAResource.name = "a"
        Project projectB = new Project()
        projectB.name = "b"
        ProjectResource projectBResource = new ProjectResource()
        projectBResource.name = "b"
        Project projectC = new Project()
        projectC.name = "c"
        ProjectResource projectCResource = new ProjectResource()
        projectCResource.name = "c"

        and:
        List<Project> allProjects = [projectA, projectB, projectC]
        projectRepository.findAll() >> allProjects

        and:
        projectConverter.toDto(projectA) >> projectAResource
        projectConverter.toDto(projectB) >> projectBResource
        projectConverter.toDto(projectC) >> projectCResource

        when:
        List<ProjectResource> result = projectResolverImpl.findAll()

        then:
        assertThat(result).hasSize(3)
    }
}
