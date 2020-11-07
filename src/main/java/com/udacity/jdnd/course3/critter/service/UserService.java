package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entities.user.Customer;
import com.udacity.jdnd.course3.critter.entities.user.Employee;
import com.udacity.jdnd.course3.critter.entities.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        try {
            return customerRepository.findById(id).get();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    public Customer findCustomerByPetId(Long id){
        try {
            return customerRepository.findCustomerByPetsId(id);
        }catch (NoSuchElementException e){
            return null;
        }
    }

    public Iterable<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id){
        try{
            return employeeRepository.findById(id).get();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        try{
            Employee employee = employeeRepository.findById(employeeId).get();
            employee.setDaysAvailable(daysAvailable);
            employeeRepository.save(employee);
        }catch (NoSuchElementException e){
            //some NoSuchElementException handling
        }
    }

    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate date) {
        List<Employee> foundEmployees = new ArrayList<>();
        Iterable<Employee> employees = employeeRepository.findEmployeeByDaysAvailable(date.getDayOfWeek());
        employees.forEach(employee -> {
            if (employee.getSkills().containsAll(skills)) {
                foundEmployees.add(employee);
            }
        });
        return foundEmployees;
    }
}
