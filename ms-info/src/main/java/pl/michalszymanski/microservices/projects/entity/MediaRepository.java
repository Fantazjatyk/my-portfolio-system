package pl.michalszymanski.microservices.projects.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long>{
}
