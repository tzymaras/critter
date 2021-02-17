package com.udacity.jdnd.course3.critter.common.converter;

import com.udacity.jdnd.course3.critter.user.dataobject.*;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter extends AbstractEntityToDTOConverter<Customer, CustomerDTO> {
    public CustomerConverter() {
        super(Customer.class, CustomerDTO.class);
    }

    @Override
    public void prepareFactory(MapperFactory mapperFactory) {
        mapperFactory.classMap(Customer.class, CustomerDTO.class)
            .mapNulls(false)
            .mapNullsInReverse(false)
            .fieldAToB("pets{id}", "petIds{}")
            .byDefault()
            .register();
    }
}
