package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entitie.user.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    List<Employee> findEmployeeByDaysAvailable(DayOfWeek dayOfWeek);
    List<Employee> findByIdIn(Long[] employeeIds);
}
