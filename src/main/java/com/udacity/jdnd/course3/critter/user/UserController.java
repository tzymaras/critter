package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.common.SimpleDTOMapper;
import com.udacity.jdnd.course3.critter.user.persistence.*;
import com.udacity.jdnd.course3.critter.user.service.EmployeeService;
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

    public UserController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = SimpleDTOMapper.map(employeeDTO, Employee.class);
        return SimpleDTOMapper.map(this.employeeService.save(employee), EmployeeDTO.class);
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return SimpleDTOMapper.map(this.employeeService.findById(employeeId), EmployeeDTO.class);
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
            .map(employee -> SimpleDTOMapper.map(employee, EmployeeDTO.class))
            .collect(Collectors.toList());
    }

}
