package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.pet.dataobject.Pet;
import com.udacity.jdnd.course3.critter.user.dataobject.Customer;
import com.udacity.jdnd.course3.critter.user.exception.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    public Customer getOwnerByPet(Pet pet) {
        Customer customer = this.customerRepository
            .findCustomerByPetsContaining(pet)
            .orElseThrow(CustomerNotFoundException::new);

        customer.setPets(customer.getPets());

        return customer;
    }

    public Customer findById(Long id) {
        return this.customerRepository
            .findById(id)
            .orElseThrow(CustomerNotFoundException::new);
    }
}
