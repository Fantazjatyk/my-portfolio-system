package pl.michalszymanski.microservices.projects.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.michalszymanski.microservices.projects.api.dto.ErrorResource;
import pl.michalszymanski.microservices.projects.api.dto.ProjectResource;
import pl.michalszymanski.microservices.projects.api.dto.ProjectsResource;
import pl.michalszymanski.microservices.projects.service.ProjectInserter;
import pl.michalszymanski.microservices.projects.service.ProjectResolver;
import pl.michalszymanski.microservices.projects.service.ProjectUpdater;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
@Api
public class ProjectController {

    @Autowired
    private ProjectResolver projectResolver;

    @Autowired
    private ProjectUpdater projectUpdater;

    @Autowired
    private ProjectInserter projectInserter;

    @GetMapping
    @ApiOperation("returns all projects, or with given name")
    @ApiResponses(value = {
            @ApiResponse(code = 500, response = ErrorResource.class, message = "error occurred")
    })
    public ProjectsResource getProjects(@RequestParam(required = false) String name) {
        List<ProjectResource> projects = projectResolver.findAll();

        if (!Objects.isNull(name)) {
            projects = projects.stream().filter(p -> name.equals(p.getName())).collect(Collectors.toList());
        }

        return new ProjectsResource(projects);
    }

    @GetMapping("{id}")
    @ApiOperation("get project with id")
    @ApiResponses(value = {
            @ApiResponse(code = 500, response = ErrorResource.class, message = "error occurred")
    })
    public ProjectResource getProject(@PathVariable Long id) {
        return projectResolver.findById(id).get();
    }

    @PostMapping("{id}")
    @ApiOperation("update project with id")
    @ApiResponses(value = {
            @ApiResponse(code = 500, response = ErrorResource.class, message = "error occurred")
    })
    public ProjectResource updateProject(@PathVariable Long id, @RequestBody @Valid ProjectResource project) {
        return projectUpdater.update(id, project);
    }

    @PutMapping
    @ApiOperation("insert new project")
    @ApiResponses(value = {
            @ApiResponse(code = 500, response = ErrorResource.class, message = "error occurred")
    })
    public ProjectResource insertProject(@RequestBody @Valid ProjectResource projectResource) {
        return projectInserter.insert(projectResource);
    }
}
