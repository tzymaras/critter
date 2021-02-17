package com.udacity.jdnd.course3.critter.common.converter;

public interface Convertable<T, U> {
    T dtoToEntity(U dto);

    U entityToDTO(T entity);
}
