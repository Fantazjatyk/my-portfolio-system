package pl.michalszymanski.microservices.projects.service

import pl.michalszymanski.microservices.projects.entity.Project
import pl.michalszymanski.microservices.projects.entity.ProjectRepository
import pl.michalszymanski.microservices.projects.api.dto.ProjectResource
import pl.michalszymanski.microservices.projects.mapping.ProjectConverter
import spock.lang.Specification

import java.time.OffsetDateTime

class ProjectInserterImplTest extends Specification {

    private ProjectInserterImpl projectInserter
    private ProjectRepository projectRepository
    private ProjectConverter projectConverter

    def setup() {
        this.projectRepository = Mock(ProjectRepository)
        this.projectConverter = Mock(ProjectConverter)
        this.projectInserter = new ProjectInserterImpl(projectRepository, projectConverter)
    }

    def "should insert new entity"() {
        given:
        ProjectResource projectResource = new ProjectResource()
        projectResource.name = "new project"
        projectResource.creationDate = OffsetDateTime.now()

        Project project = new Project()
        project.name = projectResource.name
        project.creationDate = projectResource.creationDate

        and:
        projectConverter.toEntity(_) >> project
        projectRepository.save(_) >> project

        when:
        projectInserter.insert(projectResource)

        then:
        1 * projectRepository.save(project)
    }
}
