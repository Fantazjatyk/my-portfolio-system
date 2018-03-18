package pl.michalszymanski.microservices.projects.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.michalszymanski.microservices.projects.api.dto.MeResource;
import pl.michalszymanski.microservices.projects.entity.Me;

@Mapper(componentModel = "spring")
public interface MeConverter extends DtoEntityConverter<MeResource, Me> {

    MeConverter INSTANCE = Mappers.getMapper(MeConverter.class);

}
