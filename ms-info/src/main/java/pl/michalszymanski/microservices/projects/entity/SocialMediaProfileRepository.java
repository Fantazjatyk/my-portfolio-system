package pl.michalszymanski.microservices.projects.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public interface SocialMediaProfileRepository extends JpaRepository<SocialMediaProfile, Long> {

    default List<SocialMediaProfile> findByProvider(String provider) {
        return findAll().stream().filter(el -> el.getProvider().toString().equals(provider)).collect(Collectors.toList());
    }
}
