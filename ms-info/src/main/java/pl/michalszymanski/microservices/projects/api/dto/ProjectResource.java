package pl.michalszymanski.microservices.projects.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class ProjectResource {

    @NotNull
    @Size(min = 1, max = 30)
    private String name;
    private Long id;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 500)
    private String description;
    @NotNull
    @Past
    private OffsetDateTime creationDate;
    @Valid
    @NotNull
    private List<MediaResource> medias;

}
