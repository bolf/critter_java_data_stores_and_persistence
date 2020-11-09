package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entitie.pet.Pet;
import com.udacity.jdnd.course3.critter.entitie.schedule.Schedule;
import com.udacity.jdnd.course3.critter.entitie.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entitie.user.Employee;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private UserService userService;
    @Autowired
    private PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return convertScheduleToScheduleDTO(scheduleService.save(convertScheduleDTOToSchedule(scheduleDTO)));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        scheduleService.findAll().forEach(schedule -> scheduleDTOS.add(convertScheduleToScheduleDTO(schedule)));
        return scheduleDTOS;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.findAllByPetsId(petId).stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.findAllByEmployeesId(employeeId).stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.findAllByPetsOwnerId(customerId).stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }

    /**
     * Transformation DTO -> Schedule & Schedule -> DTO
     */
    private Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO,schedule);
        schedule.setActivities(scheduleDTO.getActivities());
        if(! (scheduleDTO.getEmployeeIds() == null || scheduleDTO.getEmployeeIds().isEmpty())) {
            schedule.setEmployees(userService.findByIdIn(scheduleDTO.getEmployeeIds().toArray(new Long[scheduleDTO.getEmployeeIds().size()])));
        }
        if(!(scheduleDTO.getPetIds() == null || scheduleDTO.getPetIds().isEmpty())){
            schedule.setPets(petService.findByIdIn(scheduleDTO.getPetIds().toArray(new Long[scheduleDTO.getPetIds().size()])));
        }
        return schedule;
    }

    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule,scheduleDTO);
        if(!(schedule.getEmployees() == null || schedule.getEmployees().size() == 0)) {
            scheduleDTO.setEmployeeIds(schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        }
        if(!(schedule.getPets() == null || schedule.getPets().size() == 0)){
            scheduleDTO.setPetIds(schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        }
        return scheduleDTO;
    }

}
