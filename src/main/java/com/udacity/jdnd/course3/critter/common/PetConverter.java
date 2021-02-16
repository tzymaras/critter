package com.udacity.jdnd.course3.critter.common;

import com.udacity.jdnd.course3.critter.pet.dataobject.*;
import ma.glasnost.orika.*;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class PetConverter implements Convertable<Pet, PetDTO> {
    private final MapperFacade mapperFacade;

    public PetConverter() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Pet.class, PetDTO.class)
            .mapNulls(false)
            .mapNullsInReverse(false)
            .field("owner.id", "ownerId")
            .byDefault()
            .register();

        this.mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public Pet dtoToEntity(PetDTO dto) {
        return this.mapperFacade.map(dto, Pet.class);
    }

    @Override
    public PetDTO entityToDTO(Pet entity) {
        return this.mapperFacade.map(entity, PetDTO.class);
    }
}
