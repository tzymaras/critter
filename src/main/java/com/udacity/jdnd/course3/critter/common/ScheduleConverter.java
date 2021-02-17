package com.udacity.jdnd.course3.critter.common;

import com.udacity.jdnd.course3.critter.schedule.dataobject.*;
import ma.glasnost.orika.*;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConverter implements Convertable<Schedule, ScheduleDTO> {
    private final MapperFacade mapperFacade;

    public ScheduleConverter() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Schedule.class, ScheduleDTO.class)
            .mapNulls(false)
            .mapNullsInReverse(false)
            .field("employees{id}", "employeeIds{}")
            .field("pets{id}", "petIds{}")
            .byDefault()
            .register();

        this.mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public Schedule dtoToEntity(ScheduleDTO dto) {
        return this.mapperFacade.map(dto, Schedule.class);
    }

    @Override
    public ScheduleDTO entityToDTO(Schedule entity) {
        return this.mapperFacade.map(entity, ScheduleDTO.class);
    }
}
