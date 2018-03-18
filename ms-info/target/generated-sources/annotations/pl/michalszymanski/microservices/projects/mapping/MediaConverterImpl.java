package pl.michalszymanski.microservices.projects.mapping;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import pl.michalszymanski.microservices.projects.api.dto.MediaResource;
import pl.michalszymanski.microservices.projects.entity.Media;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-03-18T17:09:24+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
@Component
public class MediaConverterImpl implements MediaConverter {

    @Override
    public MediaResource toDto(Media entity) {
        if ( entity == null ) {
            return null;
        }

        MediaResource mediaResource = new MediaResource();

        mediaResource.setUrl( entity.getUrl() );

        return mediaResource;
    }

    @Override
    public Media toEntity(MediaResource dto) {
        if ( dto == null ) {
            return null;
        }

        Media media = new Media();

        media.setUrl( dto.getUrl() );

        return media;
    }
}
