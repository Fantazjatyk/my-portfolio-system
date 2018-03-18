package pl.michalszymanski.microservices.projects.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
public class Me {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String story;
    private String email;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SocialMediaProfile> socialMediaProfiles;

}
