package pl.michalszymanski.microservices.projects.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResource {

    private String message;

    public ErrorResource(String message) {
        this.message = message;
    }
}
