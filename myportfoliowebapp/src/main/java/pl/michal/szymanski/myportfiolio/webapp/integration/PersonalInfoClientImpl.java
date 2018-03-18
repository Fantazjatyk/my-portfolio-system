package pl.michal.szymanski.myportfiolio.webapp.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.michal.szymanski.personalinfo.api.ProjectsResource;

@Component
public class PersonalInfoClientImpl implements PersonalInfoClient {

    @Autowired
    private RestTemplate restTemplate;

    public ProjectsResource getAllProjects() {
        return restTemplate.getForObject("http://localhost:8999/projects", ProjectsResource.class);
    }
}
