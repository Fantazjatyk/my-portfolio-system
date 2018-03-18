package pl.michalszymanski.microservices.projects.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.michalszymanski.microservices.projects.entity.Media;
import pl.michalszymanski.microservices.projects.entity.Project;
import pl.michalszymanski.microservices.projects.entity.ProjectRepository;
import pl.michalszymanski.microservices.projects.api.dto.ProjectResource;
import pl.michalszymanski.microservices.projects.mapping.MediaConverter;
import pl.michalszymanski.microservices.projects.mapping.ProjectConverter;

import static com.google.common.base.MoreObjects.firstNonNull;

@Service
public class ProjectUpdaterImpl implements ProjectUpdater {

    private ProjectRepository projectRepository;
    private ProjectConverter projectConverter;
    private MediaConverter mediaConverter;

    @Autowired
    public ProjectUpdaterImpl(
            ProjectRepository projectRepository,
            ProjectConverter projectConverter,
            MediaConverter mediaConverter) {
        this.projectRepository = projectRepository;
        this.projectConverter = projectConverter;
        this.mediaConverter = mediaConverter;
    }

    @Override
    public ProjectResource update(Long id, ProjectResource newOne) {
        Project project = projectRepository.findOne(id);

        if (project != null) {
            project.setName(firstNonNull(newOne.getName(), project.getName()));
            project.setCreationDate(firstNonNull((newOne.getCreationDate()), project.getCreationDate()));
            project.setDescription(firstNonNull(newOne.getDescription(), project.getDescription()));

            if (newOne.getMedias() != null) {
                if (!CollectionUtils.isEmpty(project.getMedias())) {
                    project.getMedias().clear();
                } else {
                    project.setMedias(Lists.newArrayList());
                }

                newOne.getMedias().forEach(el -> {
                    Media media = mediaConverter.toEntity(el);
                    media.setProject(project);
                    project.getMedias().add(media);
                });
            }
        }
        Project savedProject = projectRepository.save(project);
        return projectConverter.toDto(savedProject);
    }


}
