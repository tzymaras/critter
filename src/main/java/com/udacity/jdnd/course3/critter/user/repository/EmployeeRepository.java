package com.udacity.jdnd.course3.critter.user.repository;

import com.udacity.jdnd.course3.critter.user.dataobject.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.*;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<List<Employee>> findByDaysAvailableIsAndSkillsIn(DayOfWeek dayOfWeek, Set<EmployeeSkill> employeeSkills);
}
