package pl.michalszymanski.microservices.projects.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MeRepository extends JpaRepository<Me, Long> {

    default Me findFirst() {
        return findAll().stream().findFirst().orElse(null);
    }

}
