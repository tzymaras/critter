package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.user.exception.*;
import com.udacity.jdnd.course3.critter.user.dataobject.*;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Employee findById(Long employeeId) {
        return this.employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        this.employeeRepository.findById(employeeId).ifPresentOrElse(
            employee -> {
                employee.setDaysAvailable(daysAvailable);
                this.employeeRepository.save(employee);
            },
            () -> {
                throw new EmployeeNotFoundException(employeeId);
            }
        );
    }

    public List<Employee> findEmployeesForService(DayOfWeek dayOfWeek, Set<EmployeeSkill> skills) {
        return this.employeeRepository
            .findDistinctByDaysAvailableIsAndSkillsIn(dayOfWeek, skills)
            .orElseThrow(NoAvailableEmployeeFoundException::new)
            .stream()
            .filter(employee -> employee.getSkills().containsAll(skills))
            .collect(Collectors.toList());
    }
}
