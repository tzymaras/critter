package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.common.converter.PetConverter;
import com.udacity.jdnd.course3.critter.pet.dataobject.*;
import com.udacity.jdnd.course3.critter.pet.service.PetService;
import com.udacity.jdnd.course3.critter.user.dataobject.Customer;
import com.udacity.jdnd.course3.critter.user.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;
    private final PetConverter petConverter;
    private final CustomerService customerService;

    public PetController(PetService petService, CustomerService customerService, PetConverter petConverter) {
        this.petService = petService;
        this.customerService = customerService;
        this.petConverter = petConverter;
    }

    @PostMapping
    public PetDTO savePet(@Valid @RequestBody PetDTO petDTO) {
        Customer customer = this.customerService.findById(petDTO.getOwnerId());

        Pet storedPet = this.petService.save(this.petConverter.dtoToEntity(petDTO));

        if (!customer.hasPets()) {
            customer.setPets(new ArrayList<>());
        }

        customer.getPets().add(storedPet);
        this.customerService.save(customer);

        return this.petConverter.entityToDTO(storedPet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return this.petConverter.entityToDTO(this.petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return this.petService.findAll()
            .stream()
            .map(this.petConverter::entityToDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        Customer customer = this.customerService.findById(ownerId);

        return this.petService.findPetsByOwner(customer)
            .stream()
            .map(this.petConverter::entityToDTO)
            .collect(Collectors.toList());
    }
}
