package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entities.user.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {}
