package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entities.user.Customer;
import com.udacity.jdnd.course3.critter.entities.user.Employee;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

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
}
