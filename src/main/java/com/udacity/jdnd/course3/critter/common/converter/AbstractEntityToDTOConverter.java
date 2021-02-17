package com.udacity.jdnd.course3.critter.common.converter;

import ma.glasnost.orika.*;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Base class for DTO/Entity Converters.
 * <p>
 * Implements needed methods for converting from Entities to DTOs and vice versa
 * using the Orika object mapper
 *
 * @param <T> Entity type
 * @param <U> DTO type
 */
public abstract class AbstractEntityToDTOConverter<T, U> implements Convertable<T, U> {
    private final MapperFacade mapperFacade;
    private final Class<T> entityClass;
    private final Class<U> dtoClass;

    public AbstractEntityToDTOConverter(Class<T> entityClass, Class<U> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        this.prepareFactory(mapperFactory);
        this.mapperFacade = mapperFactory.getMapperFacade();
    }

    /**
     * extending classes must register/define handling/mapping of fields with Orika components
     *
     * @param mapperFactory orika mapperFactory
     */
    public abstract void prepareFactory(MapperFactory mapperFactory);

    @Override
    public T dtoToEntity(U dto) {
        return this.mapperFacade.map(dto, this.entityClass);
    }

    @Override
    public U entityToDTO(T entity) {
        return this.mapperFacade.map(entity, this.dtoClass);
    }
}
