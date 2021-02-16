package com.udacity.jdnd.course3.critter.common;

import com.udacity.jdnd.course3.critter.user.dataobject.*;
import ma.glasnost.orika.*;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter implements Convertable<Customer, CustomerDTO> {
    private final MapperFacade mapperFacade;

    public CustomerConverter() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Customer.class, CustomerDTO.class)
            .mapNulls(false)
            .mapNullsInReverse(false)
            .fieldAToB("pets{id}", "petIds{}")
            .byDefault()
            .register();

        this.mapperFacade = mapperFactory.getMapperFacade();
    }

    public Customer dtoToEntity(CustomerDTO customerDTO) {
        return this.mapperFacade.map(customerDTO, Customer.class);
    }

    public CustomerDTO entityToDTO(Customer customer) {
        return this.mapperFacade.map(customer, CustomerDTO.class);
    }
}
