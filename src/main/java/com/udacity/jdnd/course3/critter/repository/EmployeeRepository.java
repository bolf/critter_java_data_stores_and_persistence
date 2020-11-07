package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entities.user.Employee;
import org.springframework.data.repository.CrudRepository;

import java.time.DayOfWeek;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    public Iterable<Employee> findEmployeeByDaysAvailable(DayOfWeek dayOfWeek);
}
