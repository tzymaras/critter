package com.udacity.jdnd.course3.critter.common.converter;

import com.udacity.jdnd.course3.critter.pet.dataobject.*;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class PetConverter extends AbstractEntityToDTOConverter<Pet, PetDTO> {
    public PetConverter() {
        super(Pet.class, PetDTO.class);
    }

    public void prepareFactory(MapperFactory mapperFactory) {
        mapperFactory.classMap(Pet.class, PetDTO.class)
            .mapNulls(false)
            .mapNullsInReverse(false)
            .field("owner.id", "ownerId")
            .byDefault()
            .register();
    }
}
