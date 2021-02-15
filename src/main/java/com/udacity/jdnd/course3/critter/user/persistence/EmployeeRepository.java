package com.udacity.jdnd.course3.critter.user.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.*;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<List<Employee>> findDistinctByDaysAvailableIsAndSkillsIn(DayOfWeek dayOfWeek, Set<EmployeeSkill> employeeSkills);
}
