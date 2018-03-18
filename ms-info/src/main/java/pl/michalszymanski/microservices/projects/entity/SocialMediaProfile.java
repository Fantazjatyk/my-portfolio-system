package pl.michalszymanski.microservices.projects.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class SocialMediaProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private SocialMedia provider;
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Me me;
    private String url;
}
