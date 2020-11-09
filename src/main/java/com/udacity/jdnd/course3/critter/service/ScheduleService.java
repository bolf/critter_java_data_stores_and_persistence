package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entitie.schedule.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Iterable<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findAllByEmployeesId(long employeeId) {
        return scheduleRepository.findAllByEmployeesId(employeeId);
    }

    public List<Schedule> findAllByPetsId(long petId) {
        return scheduleRepository.findAllByPetsId(petId);
    }

    public List<Schedule> findAllByPetsOwnerId(long ownerId){
        return scheduleRepository.findAllByPetsOwnerId(ownerId);
    }
}