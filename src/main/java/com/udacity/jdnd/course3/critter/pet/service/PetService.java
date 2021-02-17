package com.udacity.jdnd.course3.critter.pet.service;

import com.udacity.jdnd.course3.critter.pet.dataobject.Pet;
import com.udacity.jdnd.course3.critter.pet.exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.dataobject.Customer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
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

    public Set<Pet> findAllByIdIn(List<Long> petIds) {
        return this.petRepository.findAllByIdIn(petIds);
    }

    public Pet findById(Long id) {
        return this.petRepository.findById(id).orElseThrow(() -> new PetNotFoundException(id));
    }
}
