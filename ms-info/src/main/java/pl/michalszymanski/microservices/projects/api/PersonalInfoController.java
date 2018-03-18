package pl.michalszymanski.microservices.projects.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.michalszymanski.microservices.projects.api.dto.ErrorResource;
import pl.michalszymanski.microservices.projects.api.dto.MeResource;
import pl.michalszymanski.microservices.projects.service.MeResolver;
import pl.michalszymanski.microservices.projects.service.MeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/me")
@Api
public class PersonalInfoController {

    @Autowired
    private MeResolver meResolver;

    @GetMapping
    public MeResource getMe() {
        return meResolver.findMe();
    }

    @Autowired
    private MeService meUpdater;

    @PostMapping
    @ApiOperation("update me information")
    @ApiResponses(value = {
            @ApiResponse(code = 500, response = ErrorResource.class, message = "error occurred")
    })
    public MeResource updateMe(@RequestBody MeResource me) {
        return meUpdater.update(me);
    }

    @PutMapping
    @ApiOperation("insert new me information")
    @ApiResponses(value = {
            @ApiResponse(code = 500, response = ErrorResource.class, message = "error occurred")
    })
    public MeResource insertMe(@RequestBody @Valid MeResource me) {
        return meUpdater.insert(me);
    }
}
