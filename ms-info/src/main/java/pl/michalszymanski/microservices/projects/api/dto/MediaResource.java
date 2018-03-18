package pl.michalszymanski.microservices.projects.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MediaResource {

    @NotNull
    @Size(min = 1, max = 200)
    @URL
    private String url;

}
