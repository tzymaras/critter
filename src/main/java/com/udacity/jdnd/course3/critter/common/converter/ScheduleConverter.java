package com.udacity.jdnd.course3.critter.common.converter;

import com.udacity.jdnd.course3.critter.schedule.dataobject.*;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConverter extends AbstractEntityToDTOConverter<Schedule, ScheduleDTO> {
    public ScheduleConverter() {
        super(Schedule.class, ScheduleDTO.class);
    }

    public void prepareFactory(MapperFactory mapperFactory) {
        mapperFactory.classMap(Schedule.class, ScheduleDTO.class)
            .mapNulls(false)
            .mapNullsInReverse(false)
            .field("employees{id}", "employeeIds{}")
            .field("pets{id}", "petIds{}")
            .byDefault()
            .register();
    }
}
