package pl.michalszymanski.microservices.projects.mapping;

public interface DtoEntityConverter<D, E> {

    D toDto(E entity);

    E toEntity(D dto);

}
