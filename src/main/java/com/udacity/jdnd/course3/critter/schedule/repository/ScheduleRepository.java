package com.udacity.jdnd.course3.critter.schedule.repository;

import com.udacity.jdnd.course3.critter.pet.dataobject.Pet;
import com.udacity.jdnd.course3.critter.schedule.dataobject.Schedule;
import com.udacity.jdnd.course3.critter.user.dataobject.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findSchedulesByPetsIn(List<Pet> pets);

    List<Schedule> findSchedulesByEmployeesIn(List<Employee> employees);
}
