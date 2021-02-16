package com.udacity.jdnd.course3.critter.pet.service;

import com.udacity.jdnd.course3.critter.pet.dataobject.Pet;
import com.udacity.jdnd.course3.critter.pet.exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.dataobject.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet getPet(long petId) {
        return this.petRepository
            .findById(petId)
            .orElseThrow(() -> new PetNotFoundException(petId));
    }

    public List<Pet> findAll() {
        return this.petRepository.findAll();
    }

    public Pet save(Pet pet) {
        return this.petRepository.save(pet);
    }

    public List<Pet> findPetsByOwner(Customer customer) {
        return this.petRepository.findPetsByOwner(customer);
    }
}
