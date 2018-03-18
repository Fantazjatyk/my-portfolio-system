package pl.michalszymanski.microservices.projects.service

import pl.michalszymanski.microservices.projects.entity.Media
import pl.michalszymanski.microservices.projects.entity.Project
import pl.michalszymanski.microservices.projects.entity.ProjectRepository
import pl.michalszymanski.microservices.projects.api.dto.MediaResource
import pl.michalszymanski.microservices.projects.api.dto.ProjectResource
import pl.michalszymanski.microservices.projects.mapping.MediaConverter
import pl.michalszymanski.microservices.projects.mapping.ProjectConverter
import spock.lang.Specification

import java.time.OffsetDateTime

import static org.assertj.core.api.Assertions.assertThat

class ProjectUpdaterImplTest extends Specification {

    private ProjectUpdaterImpl projectUpdater
    private ProjectRepository projectRepository
    private ProjectConverter projectConverter
    private MediaConverter mediaConverter

    def setup() {
        projectRepository = Mock(ProjectRepository)
        projectConverter = Mock(ProjectConverter)
        mediaConverter = Mock(MediaConverter)
        projectUpdater = new ProjectUpdaterImpl(projectRepository, projectConverter, mediaConverter)
    }

    def "should update old project basing on new project data (but keep the not overridden ones)"() {
        given:
        Project existingProject = new Project()
        existingProject.id = 331
        existingProject.name = "a"
        existingProject.creationDate = OffsetDateTime.now()
        existingProject.description = "blablah"

        ProjectResource newProject = new ProjectResource()
        newProject.name = "a.v2"
        newProject.medias = [
                new MediaResource("originalMediaId": "someInternalResourceId", "format": "some format"),
                new MediaResource("originalMediaId": "someInternalResourceId2", "format": "some format2")
        ]

        and: "updated entity should be:"
        Project updatedProject = new Project()
        updatedProject.name = newProject.name
        updatedProject.id = existingProject.id
        updatedProject.creationDate = existingProject.creationDate
        existingProject.description = "new blahblah"

        and:
        projectRepository.findOne(existingProject.id) >> existingProject
        Project result

        and:
        mediaConverter.toEntity(newProject.medias[0]) >> new Media("originalMediaId": "someInternalResourceId", "format": "some format")
        mediaConverter.toEntity(newProject.medias[1]) >> new Media("originalMediaId": "someInternalResourceId2", "format": "some format2")

        when:
        projectUpdater.update(existingProject.id, newProject)

        then:
        1 * projectRepository.save(updatedProject) >> {args -> result = args[0]}
        assertThat(result.medias).hasSize(2)
        assertThat(result.medias[0].format).isEqualTo(newProject.medias[0].format)
        assertThat(result.medias[1].format).isEqualTo(newProject.medias[1].format)
        assertThat(result.medias[0].originalMediaId).isEqualTo(newProject.medias[0].originalMediaId)
        assertThat(result.medias[1].originalMediaId).isEqualTo(newProject.medias[1].originalMediaId)
    }
}
