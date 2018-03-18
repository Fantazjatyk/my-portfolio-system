package pl.michalszymanski.microservices.projects.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michalszymanski.microservices.projects.api.ResourceNotFoundException;
import pl.michalszymanski.microservices.projects.api.dto.MeResource;
import pl.michalszymanski.microservices.projects.api.dto.SocialMediaProfileResource;
import pl.michalszymanski.microservices.projects.entity.Me;
import pl.michalszymanski.microservices.projects.entity.MeRepository;
import pl.michalszymanski.microservices.projects.entity.SocialMediaProfile;
import pl.michalszymanski.microservices.projects.entity.SocialMediaProfileRepository;
import pl.michalszymanski.microservices.projects.mapping.MeConverter;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class MeService {

    @Autowired
    private MeRepository meRepository;

    @Autowired
    private MeConverter meConverter;

    @Autowired
    private SocialMediaProfileRepository socialMediaProfilesRepository;

    public MeResource update(MeResource meResource) {
        Me me = findMe();
        updateIfNotNull(meResource.getEmail(), el -> me.setEmail((String) el));
        updateIfNotNull(meResource.getFirstName(), el -> me.setFirstName((String) el));
        updateIfNotNull(meResource.getLastName(), el -> me.setLastName((String) el));
        updateSocialMediaProfiles(meResource.getSocialMediaProfiles());
        Me updated = meRepository.save(me);
        return meConverter.toDto(updated);
    }

    public MeResource insert(MeResource meResource) {
        if (findMe() != null) {
            throw new RuntimeException("Me entity already exists!");
        }

        Me me = meConverter.toEntity(meResource);
        Me updated = meRepository.save(me);
        return meConverter.toDto(updated);
    }

    private Me findMe() {
        Me me = meRepository.findFirst();

        if (me == null) {
            throw new ResourceNotFoundException();
        }

        return me;
    }

    private void updateSocialMediaProfiles(List<SocialMediaProfileResource> socialMediaProfileResources) {
        if (socialMediaProfileResources != null) {
            socialMediaProfileResources.stream().forEach(el -> {
                Optional<SocialMediaProfile> socialMediaProfile = socialMediaProfilesRepository.findByProvider(el.getProvider()).stream().findFirst();
                socialMediaProfile.ifPresent(socialMediaProfile1 -> {
                    updateIfNotNull(el.getUrl(), value -> socialMediaProfile1.setUrl((String) value));
                });
            });
        }
    }

    private void updateIfNotNull(Object newValue, Consumer<Object> consumer) {
        if (newValue != null) {
            consumer.accept(newValue);
        }
    }

}
