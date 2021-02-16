package com.udacity.jdnd.course3.critter.common;

public interface Convertable<T, U> {
    T dtoToEntity(U dto);
    U entityToDTO(T entity);
}
