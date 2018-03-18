package pl.michalszymanski.microservices.projects.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.michalszymanski.microservices.projects.entity.Media;
import pl.michalszymanski.microservices.projects.api.dto.MediaResource;

@Mapper(componentModel = "spring")
public interface MediaConverter extends DtoEntityConverter<MediaResource, Media> {

    MediaConverter INSTANCE = Mappers.getMapper(MediaConverter.class);

}
