package pl.michalszymanski.microservices.projects.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michalszymanski.microservices.projects.api.ResourceNotFoundException;
import pl.michalszymanski.microservices.projects.api.dto.MeResource;
import pl.michalszymanski.microservices.projects.entity.Me;
import pl.michalszymanski.microservices.projects.entity.MeRepository;
import pl.michalszymanski.microservices.projects.mapping.MeConverter;

@Service
public class MeResolver {

    @Autowired
    private MeRepository meRepository;

    @Autowired
    private MeConverter meConverter;

    public MeResource findMe() {
        Me me = meRepository.findFirst();

        if (me == null) {
            throw new ResourceNotFoundException();
        }

        return meConverter.toDto(me);
    }
}
