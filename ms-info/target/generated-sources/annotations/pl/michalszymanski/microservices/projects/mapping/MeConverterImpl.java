package pl.michalszymanski.microservices.projects.mapping;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import pl.michalszymanski.microservices.projects.api.dto.MeResource;
import pl.michalszymanski.microservices.projects.api.dto.SocialMediaProfileResource;
import pl.michalszymanski.microservices.projects.entity.Me;
import pl.michalszymanski.microservices.projects.entity.SocialMedia;
import pl.michalszymanski.microservices.projects.entity.SocialMediaProfile;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-03-18T17:09:25+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
@Component
public class MeConverterImpl implements MeConverter {

    @Override
    public MeResource toDto(Me entity) {
        if ( entity == null ) {
            return null;
        }

        MeResource meResource = new MeResource();

        meResource.setFirstName( entity.getFirstName() );
        meResource.setLastName( entity.getLastName() );
        meResource.setEmail( entity.getEmail() );
        meResource.setSocialMediaProfiles( socialMediaProfileListToSocialMediaProfileResourceList( entity.getSocialMediaProfiles() ) );

        return meResource;
    }

    @Override
    public Me toEntity(MeResource dto) {
        if ( dto == null ) {
            return null;
        }

        Me me = new Me();

        me.setFirstName( dto.getFirstName() );
        me.setLastName( dto.getLastName() );
        me.setEmail( dto.getEmail() );
        me.setSocialMediaProfiles( socialMediaProfileResourceListToSocialMediaProfileList( dto.getSocialMediaProfiles() ) );

        return me;
    }

    protected SocialMediaProfileResource socialMediaProfileToSocialMediaProfileResource(SocialMediaProfile socialMediaProfile) {
        if ( socialMediaProfile == null ) {
            return null;
        }

        SocialMediaProfileResource socialMediaProfileResource = new SocialMediaProfileResource();

        if ( socialMediaProfile.getProvider() != null ) {
            socialMediaProfileResource.setProvider( socialMediaProfile.getProvider().name() );
        }
        socialMediaProfileResource.setUrl( socialMediaProfile.getUrl() );

        return socialMediaProfileResource;
    }

    protected List<SocialMediaProfileResource> socialMediaProfileListToSocialMediaProfileResourceList(List<SocialMediaProfile> list) {
        if ( list == null ) {
            return null;
        }

        List<SocialMediaProfileResource> list1 = new ArrayList<SocialMediaProfileResource>( list.size() );
        for ( SocialMediaProfile socialMediaProfile : list ) {
            list1.add( socialMediaProfileToSocialMediaProfileResource( socialMediaProfile ) );
        }

        return list1;
    }

    protected SocialMediaProfile socialMediaProfileResourceToSocialMediaProfile(SocialMediaProfileResource socialMediaProfileResource) {
        if ( socialMediaProfileResource == null ) {
            return null;
        }

        SocialMediaProfile socialMediaProfile = new SocialMediaProfile();

        if ( socialMediaProfileResource.getProvider() != null ) {
            socialMediaProfile.setProvider( Enum.valueOf( SocialMedia.class, socialMediaProfileResource.getProvider() ) );
        }
        socialMediaProfile.setUrl( socialMediaProfileResource.getUrl() );

        return socialMediaProfile;
    }

    protected List<SocialMediaProfile> socialMediaProfileResourceListToSocialMediaProfileList(List<SocialMediaProfileResource> list) {
        if ( list == null ) {
            return null;
        }

        List<SocialMediaProfile> list1 = new ArrayList<SocialMediaProfile>( list.size() );
        for ( SocialMediaProfileResource socialMediaProfileResource : list ) {
            list1.add( socialMediaProfileResourceToSocialMediaProfile( socialMediaProfileResource ) );
        }

        return list1;
    }
}
