package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.common.converter.ScheduleConverter;
import com.udacity.jdnd.course3.critter.pet.dataobject.Pet;
import com.udacity.jdnd.course3.critter.pet.service.PetService;
import com.udacity.jdnd.course3.critter.schedule.dataobject.*;
import com.udacity.jdnd.course3.critter.schedule.exception.ScheduleNotPossibleException;
import com.udacity.jdnd.course3.critter.schedule.service.ScheduleService;
import com.udacity.jdnd.course3.critter.user.dataobject.*;
import com.udacity.jdnd.course3.critter.user.service.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private static final String SCHEDULE_PETS_NOT_FOUND = "given pets were not found";
    private static final  String SCHEDULE_SOME_PETS_NOT_FOUND = "some of the given pets were not found";

    private final ScheduleConverter scheduleConverter;
    private final ScheduleService scheduleService;
    private final EmployeeService employeeService;
    private final PetService petService;
    private final CustomerService customerService;

    public ScheduleController(
        ScheduleConverter scheduleConverter,
        ScheduleService scheduleService,
        EmployeeService employeeService,
        PetService petService,
        CustomerService customerService
    ) {
        this.scheduleConverter = scheduleConverter;
        this.scheduleService = scheduleService;
        this.employeeService = employeeService;
        this.petService = petService;
        this.customerService = customerService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = this.scheduleConverter.dtoToEntity(scheduleDTO);

        List<Long> schedulePetIds = schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList());
        Set<Pet> storedPets = this.petService.findAllByIdIn(schedulePetIds);

        if (storedPets.isEmpty()) {
            throw new ScheduleNotPossibleException(SCHEDULE_PETS_NOT_FOUND);
        }

        boolean storedPetIdsMatchSchedulePetIds = storedPets.stream().map(Pet::getId).collect(Collectors.toSet()).containsAll(schedulePetIds);
        if (!storedPetIdsMatchSchedulePetIds) {
            throw new ScheduleNotPossibleException(SCHEDULE_SOME_PETS_NOT_FOUND);
        }

        List<Long> scheduleEmployeeIds = schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList());
        List<Employee> storedEmployees = this.employeeService.findAllByIds(scheduleEmployeeIds);

        Schedule storedSchedule = this.scheduleService.createSchedule(schedule);
        this.persistScheduleToEmployees(storedEmployees, storedSchedule);
        this.persistScheduleToPets(storedPets, storedSchedule);

        return this.scheduleConverter.entityToDTO(storedSchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return this.scheduleService.findAll()
            .stream()
            .map(this.scheduleConverter::entityToDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        Pet pet = this.petService.findById(petId);

        return this.scheduleService.findAllSchedulesByPets(List.of(pet))
            .stream()
            .map(this.scheduleConverter::entityToDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        Employee scheduleEmployee = this.employeeService.findById(employeeId);

        return this.scheduleService.findAllSchedulesByEmployee(scheduleEmployee)
            .stream()
            .map(this.scheduleConverter::entityToDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        Customer customer = this.customerService.findById(customerId);
        List<Pet> customerPets = this.petService.findPetsByOwner(customer);

        return this.scheduleService.findAllSchedulesByPets(customerPets)
            .stream()
            .map(this.scheduleConverter::entityToDTO)
            .collect(Collectors.toList());
    }

    private void persistScheduleToPets(Set<Pet> petList, Schedule schedule) {
        petList.forEach(pet -> {
            if (null == pet.getSchedules()) {
                pet.setSchedules(new ArrayList<>());
            }

            pet.getSchedules().add(schedule);
            this.petService.save(pet);
        });
    }

    private void persistScheduleToEmployees(List<Employee> employees, Schedule schedule) {
        employees.forEach(employee -> {
            if (null == employee.getSchedules()) {
                employee.setSchedules(new ArrayList<>());
            }

            employee.getSchedules().add(schedule);
            this.employeeService.save(employee);
        });
    }
}
