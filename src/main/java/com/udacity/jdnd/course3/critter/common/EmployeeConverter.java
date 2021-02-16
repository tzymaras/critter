package com.udacity.jdnd.course3.critter.common;

import com.udacity.jdnd.course3.critter.user.dataobject.*;
import ma.glasnost.orika.*;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter implements Convertable<Employee, EmployeeDTO> {
    private final MapperFacade mapperFacade;

    public EmployeeConverter() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Employee.class, EmployeeDTO.class)
            .mapNulls(false)
            .mapNullsInReverse(false)
            .byDefault()
            .register();

        this.mapperFacade = mapperFactory.getMapperFacade();
    }

    public Employee dtoToEntity(EmployeeDTO employeeDTO) {
        return this.mapperFacade.map(employeeDTO, Employee.class);
    }

    public EmployeeDTO entityToDTO(Employee employee) {
        return this.mapperFacade.map(employee, EmployeeDTO.class);
    }
}
