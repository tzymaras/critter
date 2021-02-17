package com.udacity.jdnd.course3.critter.common.converter;

import com.udacity.jdnd.course3.critter.user.dataobject.*;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter extends AbstractEntityToDTOConverter<Employee, EmployeeDTO> {
    public EmployeeConverter() {
        super(Employee.class, EmployeeDTO.class);
    }

    @Override
    public void prepareFactory(MapperFactory mapperFactory) {
        mapperFactory.classMap(Employee.class, EmployeeDTO.class)
            .mapNulls(false)
            .mapNullsInReverse(false)
            .byDefault()
            .register();
    }
}
