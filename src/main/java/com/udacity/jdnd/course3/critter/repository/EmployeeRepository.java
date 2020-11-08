package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entities.user.Employee;
import org.springframework.data.repository.CrudRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    Iterable<Employee> findEmployeeByDaysAvailable(DayOfWeek dayOfWeek);

    List<Employee> findByIdIn(Long[] employeeIds);
}
