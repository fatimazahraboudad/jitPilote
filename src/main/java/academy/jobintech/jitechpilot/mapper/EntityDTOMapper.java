package academy.jobintech.jitechpilot.mapper;

import java.util.List;


import java.util.List;


public interface EntityDTOMapper <E,D>{
    D toDto(E entity);
    E toEntity(D dto);

    default List<D> toDtos(List<E> entities){
        if(entities==null) return null;
        return  entities.stream().map(this::toDto).toList();
    }

    default List<E> toEntities(List<D> dtos){
        if(dtos==null) return null;
        return  dtos.stream().map(this::toEntity).toList();
    }
}

