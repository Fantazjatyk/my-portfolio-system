package pl.michalszymanski.microservices.projects.api;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.michalszymanski.microservices.projects.api.dto.ErrorResource;

@RestControllerAdvice
@Api
public class ControllerAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResource onException(Exception ex) {
        return new ErrorResource("Some error occurred: " + ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity onResourceNotFoundException(ResourceNotFoundException rnfe) {
        return ResponseEntity.notFound().build();
    }
}
