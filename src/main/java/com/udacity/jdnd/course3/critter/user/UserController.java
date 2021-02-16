package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.common.*;
import com.udacity.jdnd.course3.critter.pet.dataobject.Pet;
import com.udacity.jdnd.course3.critter.pet.service.PetService;
import com.udacity.jdnd.course3.critter.user.dataobject.*;
import com.udacity.jdnd.course3.critter.user.service.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final EmployeeService employeeService;
    private final CustomerService customerService;
    private final PetService petService;

    private final CustomerConverter customerConverter;
    private final EmployeeConverter employeeConverter;

    public UserController(
        EmployeeService employeeService,
        CustomerService customerService,
        PetService petService,
        CustomerConverter customerConverter,
        EmployeeConverter employeeConverter
    ) {
        this.employeeService = employeeService;
        this.customerService = customerService;
        this.petService = petService;
        this.customerConverter = customerConverter;
        this.employeeConverter = employeeConverter;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = this.customerConverter.dtoToEntity(customerDTO);
        Customer storedCustomer = this.customerService.save(customer);
        return this.customerConverter.entityToDTO(storedCustomer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        return this.customerService.findAll()
            .stream()
            .map(this.customerConverter::entityToDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        Pet pet = this.petService.getPet(petId);
        Customer customer = this.customerService.getOwnerByPet(pet);
        return this.customerConverter.entityToDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@NotEmpty @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = this.employeeConverter.dtoToEntity(employeeDTO);
        return this.employeeConverter.entityToDTO(this.employeeService.save(employee));
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return this.employeeConverter.entityToDTO(this.employeeService.findById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@NotEmpty @RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        this.employeeService.setAvailability(daysAvailable, employeeId);
    }

    @PostMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@Valid @RequestBody EmployeeRequestDTO dto) {
        return this.employeeService
            .findEmployeesForService(dto.getDate().getDayOfWeek(), dto.getSkills())
            .stream()
            .map(this.employeeConverter::entityToDTO)
            .collect(Collectors.toList());
    }

}
