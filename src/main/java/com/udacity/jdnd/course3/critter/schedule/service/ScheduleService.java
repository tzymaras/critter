package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.pet.dataobject.Pet;
import com.udacity.jdnd.course3.critter.schedule.dataobject.Schedule;
import com.udacity.jdnd.course3.critter.schedule.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.dataobject.Employee;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule createSchedule(Schedule schedule) {
        return this.scheduleRepository.save(schedule);
    }

    public List<Schedule> findAll() {
        return this.scheduleRepository.findAll();
    }

    public List<Schedule> findAllSchedulesByPets(List<Pet> petList) {
        return this.scheduleRepository.findSchedulesByPetsIn(petList);
    }

    public List<Schedule> findAllSchedulesByEmployee(Employee employee) {
        return this.scheduleRepository.findSchedulesByEmployeesContains(employee);
    }
}
