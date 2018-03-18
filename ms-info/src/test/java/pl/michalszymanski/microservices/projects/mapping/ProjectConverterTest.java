package pl.michalszymanski.microservices.projects.mapping;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.michalszymanski.microservices.projects.entity.Media;
import pl.michalszymanski.microservices.projects.entity.Project;
import pl.michalszymanski.microservices.projects.api.dto.MediaResource;
import pl.michalszymanski.microservices.projects.api.dto.ProjectResource;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ProjectConverterTest {

    private ProjectConverter projectConverter;
    private MediaConverter mediaConverter;

    @Before
    public void setUp() throws Exception {
        this.mediaConverter = mock(MediaConverter.class);
        this.projectConverter = new ProjectConverter(mediaConverter);
        given(mediaConverter.toDto(any())).willReturn(new MediaResource());
        given(mediaConverter.toEntity(any())).willReturn(new Media());
    }

    @Test
    public void toDto() {
        //given
        Project project = new Project();
        project.setName("crawler");
        project.setCreationDate(OffsetDateTime.now());
        project.setDescription("some description");
        project.setMedias(Lists.newArrayList(new Media(), new Media()));
        project.setId(new Long(43));

        //when
        ProjectResource projectResource = projectConverter.toDto(project);

        //then
        assertThat(projectResource.getName()).isNotBlank();
        assertThat(projectResource.getDescription()).isNotNull();
        assertThat(projectResource.getCreationDate()).isEqualTo(project.getCreationDate());
        assertThat(projectResource.getId()).isEqualTo(project.getId());
        assertThat(projectResource.getMedias()).hasSize(2);
    }

    @Test
    public void toEntity() {
        //given
        ProjectResource projectResource = new ProjectResource();
        projectResource.setId(new Long(3432));
        projectResource.setCreationDate(OffsetDateTime.now());
        projectResource.setDescription("some description");
        projectResource.setName("crawler");
        projectResource.setMedias(Lists.newArrayList(new MediaResource(), new MediaResource()));

        //when
        Project project = projectConverter.toEntity(projectResource);

        //then
        assertThat(project.getName()).isNotBlank();
        assertThat(project.getDescription()).isNotNull();
        assertThat(project.getCreationDate()).isEqualTo(projectResource.getCreationDate());
        assertThat(project.getId()).isNull();
        assertThat(project.getMedias()).hasSize(2);
    }
}