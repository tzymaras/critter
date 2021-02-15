package com.udacity.jdnd.course3.critter.common;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SimpleDTOMapper {
    public static <T, U> U map(T source, Class<U> target) {
        U entity = BeanUtils.instantiateClass(target);
        BeanUtils.copyProperties(source, entity);
        return entity;
    }
}
