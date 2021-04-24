package com.sparkers.companymanager.converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface GenericsConverter<Entity, Dto> {

    Dto entityToDto(Entity entity);

    Entity dtoToEntity(Dto dto);

    default List<Dto> entityListToDtoList(Collection<Entity> entityList) {
        return entityList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    default List<Entity> dtoListToEntityList(Collection<Dto> dtoList) {
        return dtoList.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}