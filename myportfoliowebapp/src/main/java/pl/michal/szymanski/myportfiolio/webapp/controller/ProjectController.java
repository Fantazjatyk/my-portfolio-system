package pl.michal.szymanski.myportfiolio.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.michal.szymanski.myportfiolio.webapp.integration.PersonalInfoClient;
import pl.michal.szymanski.personalinfo.api.ProjectsResource;

@Controller
@RequestMapping("/projects/")
public class ProjectController {

    @Autowired
    private PersonalInfoClient personalInfoClient;

    @GetMapping
    @ResponseBody
    public ProjectsResource getAllProjects() {
        return personalInfoClient.getAllProjects();
    }
}
