package pl.michalszymanski.microservices.projects.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SocialMediaProfileResource {

    @NotNull
    private String provider;
    @NotNull
    @URL
    private String url;
}
